import org.dom4j.io.STAXEventReader;

public class NumUtils {

    public static byte[] intToByte(int length)
    {
        byte[] bytes = new byte[4];
        for(int i=0; i<4; i++)
        {
            bytes[i] = (byte)(length % 256);
            length >>= 8;
        }
        return bytes;
    }

    public static int getNum(byte byteData)
    {
        int num = 0;
        if(byteData<0)
        {
            num = byteData + 256;
        }else {
            num = byteData;
        }
        return num;
    }

    public static int byteToInt(byte[] bytes)
    {
        int length = getNum(bytes[0]);
        int cur = getNum(bytes[1]);
        length = cur<<8 | length;
        cur = getNum(bytes[2]);
        length = cur<<16 | length;
        cur = getNum(bytes[3]);
        length = cur<<24 | length;
        return length;
    }
}
