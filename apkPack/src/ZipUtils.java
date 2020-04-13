import java.io.*;
import java.util.Enumeration;
import java.util.zip.*;

public class ZipUtils {

    public static void unZip(String srcFilePath, String destDirPath) throws RuntimeException {
        File srcFile = new File(srcFilePath);
        // 判断源文件是否存在
        if (!srcFile.exists()) {
            throw new RuntimeException(srcFile.getPath() + "所指文件不存在");
        }
        // 开始解压
        ZipFile zipFile = null;
        try {
            zipFile = new ZipFile(srcFile);
            Enumeration<?> entries = zipFile.entries();
            while (entries.hasMoreElements()) {
                ZipEntry entry = (ZipEntry) entries.nextElement();
                System.out.println("解压" + entry.getName());
                // 如果是文件夹，就创建个文件夹
                if (entry.isDirectory()) {
                    String dirPath = destDirPath + "/" + entry.getName();
                    File dir = new File(dirPath);
                    dir.mkdirs();
                } else {
                    // 如果是文件，就先创建一个文件，然后用io流把内容copy过去
                    File targetFile = new File(destDirPath + "/" + entry.getName());
                    // 保证这个文件的父文件夹必须要存在
                    if(!targetFile.getParentFile().exists()){
                        targetFile.getParentFile().mkdirs();
                    }
                    targetFile.createNewFile();
                    // 将压缩文件内容写入到这个文件中
                    InputStream is = zipFile.getInputStream(entry);
                    FileOutputStream fos = new FileOutputStream(targetFile);
                    int len;
                    byte[] buf = new byte[1024];
                    while ((len = is.read(buf)) != -1) {
                        fos.write(buf, 0, len);
                    }
                    // 关流顺序，先打开的后关闭
                    fos.close();
                    is.close();
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("unzip error from ZipUtils", e);
        } finally {
            if(zipFile != null){
                try {
                    zipFile.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void toZip(String srcPathName, String zipFileName){
        File file = new File(srcPathName);
        File zipFile = new File(zipFileName);
        if (!file.exists()) throw new RuntimeException(srcPathName + "不存在！");
        try
        {
            FileOutputStream fileOutputStream = new FileOutputStream(zipFile);
            CheckedOutputStream cos = new CheckedOutputStream(fileOutputStream, new CRC32());
            ZipOutputStream out = new ZipOutputStream(cos);
            String basedir = ".";
            File[] files = file.listFiles();
            for (int i = 0; i < files.length; i++)
            {
                compress(files[i], out, "");
            }
            out.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    private static void compress(File file, ZipOutputStream out, String basedir)
    {
        /* 判断是目录还是文件 */
        if (file.isDirectory())
        {
            System.out.println("压缩：" + basedir + file.getName());
            compressDirectory(file, out, basedir);
        }
        else
        {
            compressFile(file, out, basedir);
        }
    }

    // 压缩目录
    private static void compressDirectory(File dir, ZipOutputStream out, String basedir)
    {
        if (!dir.exists()) return;
        File[] files = dir.listFiles();
        for (int i = 0; i < files.length; i++)
        {
            compress(files[i], out, basedir + dir.getName() + "/");
        }
    }

    // 压缩文件
    private static void compressFile(File file, ZipOutputStream out, String basedir)
    {
        if (!file.exists())
        {
            return;
        }
        try
        {
            BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
            ZipEntry entry = new ZipEntry(basedir + file.getName());
            out.putNextEntry(entry);
            int count;
            byte data[] = new byte[1024];
            while ((count = bis.read(data, 0, 1024)) != -1)
            {
                out.write(data, 0, count);
            }
            bis.close();
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }
}
