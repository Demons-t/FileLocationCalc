import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

public class CMDUtils {
    // 执行cmd命令
    public static void runCMD(String path){
        try {
            // cmd.exe
            // Runtime.getRuntime().exec(cmd);
            String cmd = "cmd.exe /c "+path;
            // 执行命令
            Process process = Runtime.getRuntime().exec(cmd);
            // 获取命令行中输出的数据
            BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line = null;
            // 循环读取输入流中的数据
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
            if (br == null)
                br.close();
            // 等待命令行结束
            process.waitFor();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    // 切换工作目录，执行cmd命令
    public static void runCMD(String path, String dir){
        try {
            // cmd.exe
            // Runtime.getRuntime().exec(cmd);
            String cmd = "cmd.exe /c "+path;
            // 执行命令
            Process process = Runtime.getRuntime().exec(cmd,null,new File(dir));
            // 获取命令行中输出的数据
            BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line = null;
            // 循环读取输入流中的数据
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
            if (br == null)
                br.close();
            // 等待命令行结束
            process.waitFor();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
