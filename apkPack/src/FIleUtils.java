import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

public class FIleUtils {

    public static void writeDexFile(byte[] bytesDex, String path)
    {
        try {
            FileOutputStream outputStream = new FileOutputStream(new File(path));
            outputStream.write(bytesDex);
            outputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static byte[] readDexFile(String path){
        try {
            int byteread = 0;
            File oldfile = new File(path);
            if (oldfile.exists()) { //文件存在时
                InputStream inStream = new FileInputStream(path); //读入原文件
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                byte[] buffer = new byte[1024];
                int length = 0;
                while ((byteread = inStream.read(buffer)) != -1) {
                    bos.write(buffer, 0, byteread);
                }
                inStream.close();
                return bos.toByteArray();
            }
        } catch (Exception e) {
            System.out.println("复制单个文件操作出错");
            e.printStackTrace();
        }
        return null;
    }


    // 拷贝zip中的文件到新的路径
    public static byte[] readDexFromApk(String path) {
        try {
            // 1. 获取ZipFile对象
            ZipFile zf = new ZipFile(path);
            String name = "classes.dex";
            InputStream in = new BufferedInputStream(new FileInputStream(path));
            // 2. 创建zip输入流
            ZipInputStream zin = new ZipInputStream(in);
            ZipEntry ze;
            // 3. 通过zip输入流，循环获取Zip文件中的对象
            while ((ze = zin.getNextEntry()) != null) {
                if (ze.isDirectory()) {
                } else {
                    // 如果不是目录，判断其名称是否为classes.dex
                    if (!name.equals(ze.getName())) {
                        continue;
                    }
                    ByteArrayOutputStream bos = new ByteArrayOutputStream();
                    InputStream inputStream = zf.getInputStream(ze);
                    int len = 0;
                    int count = 0;
                    byte[] bytes = new byte[1024];
                    while ((len = inputStream.read(bytes)) != -1) {
                        bos.write(bytes, 0, len);
                        count += len;
                    }
                    System.out.println("文件总大小 " + ze.getName() + " : " + count + " bytes");
                    return bos.toByteArray();
                }
            }
            zin.closeEntry();
        } catch (Exception e) {
            System.out.println("复制单个文件操作出错");
            e.printStackTrace();
        }
        return null;
    }

    // 拷贝zip中的文件到新的路径
    public static void copyFileFromZip(String apkPath, String newPath) {
        try {
            // 1. 获取ZipFile对象
            ZipFile zf = new ZipFile(apkPath);
            String name = "classes.dex";
            InputStream in = new BufferedInputStream(new FileInputStream(apkPath));
            // 2. 创建zip输入流
            ZipInputStream zin = new ZipInputStream(in);
            ZipEntry ze;
            // 3. 通过zip输入流，循环获取Zip文件中的对象
            while ((ze = zin.getNextEntry()) != null) {
                if (ze.isDirectory()) {
                } else {
                    // 如果不是目录，判断名称是否为classes.dex
                    if (!name.equals(ze.getName())){
                        continue;
                    }
                    // 找到classes.dex，进行保存
                    // 没有assets目录则创建
                    File file = new File(newPath);
                    if (!file.exists()) file.mkdir();
                    // ze.getSize() 获取原始大小
                    // 循环读取classes.dex，循环写入到assets中，保存文件名为src.dex
                    FileOutputStream outputStream = new FileOutputStream(newPath+ File.separator+"src.dex");
                    InputStream inputStream = zf.getInputStream(ze);
                    int len = 0;
                    int count = 0;
                    byte[] bytes = new byte[1024];
                    while( (len = inputStream.read(bytes)) != -1){
                        outputStream.write(bytes,0,len);
                        count += len;
                    }
                    System.out.println("文件总大小 " + ze.getName() + " : " + count + " bytes");
                    outputStream.close();
                    break;
                }
            }
            zin.closeEntry();
        }
        catch (Exception e) {
            System.out.println("复制单个文件操作出错");
            e.printStackTrace();
        }
    }

    // 删除目录，包括文件
    public static void deleteFolder(String delPath) {
        File file = new File(delPath);
        deleteFile(file);
    }

    // 删除文件还有目录
    public static void deleteFile(File file) {
        try {
            if (file.exists()) {//判断文件是否存在
                if (file.isFile()) {//判断是否是文件
                    file.delete();//删除文件
                } else if (file.isDirectory()) {//否则如果它是一个目录
                    File[] files = file.listFiles();//声明目录下所有的文件 files[];
                    for (int i = 0; i < files.length; i++) {//遍历目录下所有的文件
                        deleteFile(files[i]);//把每个文件用这个方法进行迭代
                    }
                    file.delete();//删除文件夹
                }
            } else {
                System.out.println("所删除的文件不存在");
            }
        } catch (Exception e) {
            System.out.println("复制整个文件夹内容操作出错");
            e.printStackTrace();
        }
    }

    // 拷贝目录中的文件到新的路径中
    public static void copyFolder(String oldPath, String newPath) {
        try {
            (new File(newPath)).mkdirs();
            File a = new File(oldPath);
            String[] file = a.list();
            File temp = null;
            for(int i = 0; i < file.length; ++i) {
                if (oldPath.endsWith(File.separator)) {
                    temp = new File(oldPath + file[i]);
                } else {
                    temp = new File(oldPath + File.separator + file[i]);
                }
                if (temp.isFile()) {
                    FileInputStream input = new FileInputStream(temp);
                    FileOutputStream output = new FileOutputStream(newPath + "/" + temp.getName().toString());
                    byte[] b = new byte[1024];
                    int len;
                    while((len = input.read(b)) != -1) {
                        output.write(b, 0, len);
                    }
                    output.flush();
                    output.close();
                    input.close();
                }
                if (temp.isDirectory()) {
                    copyFolder(oldPath + "/" + file[i], newPath + "/" + file[i]);
                }
            }
        } catch (Exception var10) {
            System.out.println("复制整个文件夹内容操作出错");
            var10.printStackTrace();
        }
    }

    public static void copyFile(String oldPath, String newPath) {
        try {
            File file = new File(oldPath);
            FileInputStream input = new FileInputStream(file);
            FileOutputStream output = new FileOutputStream(newPath + "/" + file.getName().toString());
            byte[] b = new byte[1024];
            int len;
            while((len = input.read(b)) != -1) {
                output.write(b, 0, len);
            }
            output.flush();
            output.close();
            input.close();
        } catch (Exception var7) {
            System.out.println("复制整个文件夹内容操作出错");
            var7.printStackTrace();
        }
    }
}
