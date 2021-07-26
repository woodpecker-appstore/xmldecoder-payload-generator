import java.beans.XMLDecoder;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;

public class XMLDecoderTest {
    public static void main(String[] args) throws Exception{
        //new com.sun.org.apache.bcel.internal.util.ClassLoader().loadClass("").getConstructor(new Class[]{}).newInstance(new Object[]{});
        File file = new File("xmls/bcel2.xml");
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
        XMLDecoder xd = new XMLDecoder(bis);
        Object obj = xd.readObject();
        xd.close();
    }
}
