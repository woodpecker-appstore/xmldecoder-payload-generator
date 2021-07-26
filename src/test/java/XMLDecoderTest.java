import java.beans.XMLDecoder;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;

public class XMLDecoderTest {
    public static void main(String[] args) throws Exception{
        File file = new File("xmls/js.xml");
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
        XMLDecoder xd = new XMLDecoder(bis);
        Object obj = xd.readObject();
        xd.close();
    }
}
