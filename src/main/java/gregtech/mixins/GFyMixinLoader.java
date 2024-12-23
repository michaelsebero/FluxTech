package gregfluxology.mixins;

import net.minecraftforge.fml.common.Loader;

import com.google.common.collect.ImmutableList;
import zone.rong.mixinbooter.ILateMixinLoader;

import java.util.List;
import java.util.stream.Collectors;

public class GFyMixinLoader implements ILateMixinLoader {

    public static final List<String> modMixins = ImmutableList.of("theoneprobe", "gregtech");

    @Override
    public List<String> getMixinConfigs() {
        return modMixins.stream().map(mod -> "mixins.gregfluxology." + mod + ".json").collect(Collectors.toList());
    }

    @Override
    public boolean shouldMixinConfigQueue(String mixinConfig) {
        String[] parts = mixinConfig.split("\\.");
        return parts.length != 4 || shouldEnableModMixin(parts[2]);
    }

    public static boolean shouldEnableModMixin(String mod) {
        return Loader.isModLoaded(mod);
    }
}
