package world.ntdi.custyml;

import org.bukkit.plugin.java.JavaPlugin;
import world.ntdi.custyml.ymlfiles.ShutdownListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Java plugin for creating custom yml files. Contains the automatically saving files.
 */
public final class CustomYaml extends JavaPlugin {

    public static List<ShutdownListener> listeners = new ArrayList<>();

    // If the yaml files should save automatically on stop.
    public static boolean save = true;

    @Override
    public void onDisable() {
        if (save) listeners.forEach(ShutdownListener::shutdown);
    }
}
