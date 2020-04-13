import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.zip.Adler32;

public class DexFileUtils {

    public static void patchDexHeader(byte[] newDexFIle, int length)
    {
        // 修正文件大小
        byte[] lengths = NumUtils.intToByte(length);
        System.arraycopy(lengths,0,newDexFIle,8+4+20,4);
        // 修正siganature
        byte[] siganature = getDexFileSiganature(newDexFIle);
        System.arraycopy(siganature,0,newDexFIle,8+4,20);
        // 修正checksum
        byte[] checksums = getDexFileCheckSum(newDexFIle);
        System.arraycopy(checksums,0,newDexFIle,8,4);
    }

    private static byte[] getDexFileCheckSum(byte[] newDexFIle) {
        // 创建Adler32对象
        Adler32 adler = new Adler32();
        // 计算缓冲区校验和
        adler.update(newDexFIle,12,newDexFIle.length-12);
        // 获取值
        long value = adler.getValue();
        int data = (int)value;
        // 将整型转为数组
        byte[] checksums = new byte[4];
        for(int i=0; i< 4; i++)
        {
            checksums[i] = (byte)(data % 256);
            data >>= 8;
        }
        return checksums;
    }

    private static byte[] getDexFileSiganature(byte[] newDexFIle) {
        try {
            // 获取SHA-1摘要对象
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            int startOffset = 8+4+20;
            // 计算缓冲区摘要
            md.update(newDexFIle,startOffset,newDexFIle.length-startOffset);
            // 获取摘要
            byte[] siganature = md.digest();
            // 返回摘要
            return siganature;
        }catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }
}
