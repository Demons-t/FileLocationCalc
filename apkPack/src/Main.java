import com.sun.deploy.util.StringUtils;
import sun.net.www.protocol.file.FileURLConnection;

import java.io.File;

public class Main {

    public static void main(String[] args) {
        makeDexFiles("dummy.dex","test.apk","classes.dex");
        pack();
    }

    public static void pack()
    {
        // 反编译apk的路径
        String fileName = "test.apk";
        // 获取当前路径
        String dir = System.getProperty("user.dir");
        System.out.println(dir);
        // 构造路径
        String filePath = dir + File.separator + fileName;
        // 去掉扩展名
        String strDir = StringsUtils.getFileNameNoEx(filePath);
        // 运行apktool 反编译apk
        System.err.println("1. 反编译apk...");
        CMDUtils.runCMD("java -jar apktool.jar d " + filePath);
        System.err.println("1. 反编译apk完成...");
        // 修改xml文件
        String xmlPath = strDir+ File.separator + "AndroidManifest.xml";
        System.err.println("2. 修改清单文件...");
        XMLUtils.changleApplication(xmlPath);
        System.err.println("2. 修改清单文件完成...");
        System.err.println("3. 拷贝源lib到lib目录...");
        String strLibDir = strDir + File.separator + "lib";
        FIleUtils.deleteFolder(strLibDir);
        String strSrcLibDir = dir + File.separator + "lib";
        FIleUtils.copyFolder(strSrcLibDir, strLibDir);
        System.err.println("3. 拷贝源lib到lib目录完成...");
        // 重打包
        System.err.println("4. 重打包...");
        CMDUtils.runCMD("java -jar apktool.jar b "+strDir+" -o testpack.apk");
        System.err.println("4. 重打包完成...");
        // 解压缩
        ZipUtils.unZip("testpack.apk","testpack");
        // 将dummydex覆盖classes.dex
        FIleUtils.copyFile("classes.dex","testpack");
        // 压缩
        ZipUtils.toZip("testpack","testpack-p.apk");
        // 签名
        String outPath = dir+File.separator +"testpack-p.apk";
        String path1 = dir + File.separator + "testpack.apk";
        String outSignPath = dir+File.separator +"testpack_signed.apk";
        System.err.println("5. 签名...");
        CMDUtils.runCMD("java -jar signapk.jar testkey.x509.pem testkey.pk8 " + outPath +" "+outSignPath,"sign");
        System.err.println("5. 签名完成...");
        // 收尾
        System.err.println("6. 收尾...");
        FIleUtils.deleteFolder(strDir);
        FIleUtils.deleteFolder("testpack");
        FIleUtils.deleteFile(new File(outPath));
        FIleUtils.deleteFile(new File(path1));
        System.err.println("6. 收尾完成...");
        //输出文件路径
        System.err.println("7.已加固文件：" +outSignPath);
    }

    public static void makeDexFiles(String dummyDexPath, String apkPath, String newDexPath) {
        // 1.读取源apk中dex文件
        byte apkdex[] = FIleUtils.readDexFromApk(apkPath);
        // 加密源dex文件
        encodeDex(apkdex);
        System.out.println("apk中的dex大小：" + apkdex.length + "bytes");
        // 2.读取dummydex文件
        byte dummydex[] = FIleUtils.readDexFile(dummyDexPath);
        System.out.println("dummy dex大小：" + dummydex.length + "bytes");
        // 3. 合成新的dex文件
        // 计算长度
        int newDexLen = dummydex.length + apkdex.length + 4;
        // 申请足够缓冲区
        byte newDexFile[] = new byte[newDexLen];
        // 拷贝傀儡dex到缓冲区中
        System.arraycopy(dummydex, 0, newDexFile, 0, dummydex.length);
        // 拷贝加密之后的源dex到缓冲区中,到傀儡dex后面
        System.arraycopy(apkdex, 0, newDexFile, dummydex.length, apkdex.length);
        // 拷贝源dex文件长度到缓冲区中
        byte[] apkLen = NumUtils.intToByte(apkdex.length);
        System.arraycopy(apkLen, 0, newDexFile, dummydex.length + apkdex.length, 4);
        //4. 修改dex文件头
        DexFileUtils.patchDexHeader(newDexFile, newDexLen);
        // 5. 保存新的文件
        FIleUtils.writeDexFile(newDexFile, newDexPath);
    }

    private static void encodeDex(byte[] apkDex) {

    }
}
