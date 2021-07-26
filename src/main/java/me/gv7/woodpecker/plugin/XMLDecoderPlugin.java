package me.gv7.woodpecker.plugin;

import me.gv7.woodpecker.plugin.payloads.*;

import java.util.ArrayList;
import java.util.List;

public class XMLDecoderPlugin implements IHelperPlugin {
    public static IHelperPluginCallbacks callbacks;
    public static IPluginHelper pluginHelper;

    public void HelperPluginMain(IHelperPluginCallbacks helperPluginCallbacks) {
        this.callbacks = helperPluginCallbacks;
        this.pluginHelper = callbacks.getPluginHelper();
        callbacks.setHelperPluginName("XMLDecoder payload generator");
        callbacks.setHelperPluginVersion("0.1.0");
        callbacks.setHelperPluginAutor("woodpecker-org");
        callbacks.setHelperPluginDescription("Java XMLDecoder反序列化荷载生成器");
        List<IHelper> payloadGeneratorList = new ArrayList<IHelper>();
        payloadGeneratorList.add(new SleepPayloadGenerator());
        payloadGeneratorList.add(new DnslogPayloadGenerator());
        payloadGeneratorList.add(new HttplogPayloadGenerator());
        payloadGeneratorList.add(new SocketlogPayloadGenerator());
        payloadGeneratorList.add(new ExecuteCommandPayloadGenerator());
        payloadGeneratorList.add(new JndiInjectPayloadGenerator());
        payloadGeneratorList.add(new BCELPayloadGenerator());
        payloadGeneratorList.add(new LoadJarPayloadGenerator());
        payloadGeneratorList.add(new EvalJavaScriptPayloadGenerator());
        callbacks.registerHelper(payloadGeneratorList);
    }
}
