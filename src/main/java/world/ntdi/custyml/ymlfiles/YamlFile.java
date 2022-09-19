package world.ntdi.custyml.ymlfiles;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import world.ntdi.custyml.CustomYaml;

import java.io.File;

/**
 * Class for making custom yml files.
 */
public abstract class YamlFile implements ShutdownListener {
    private File file;
    private YamlConfiguration config;

    /**
     * Create a custom YML file.
     * @param name Name of the file. **Do not include the extension (.yml)**
     * @param instance Instance of your {@code JavaPlugin}
     */
    public YamlFile(String name, JavaPlugin instance) {
        if (!instance.getDataFolder().exists()) instance.getDataFolder().mkdirs();
        this.file = new File(instance.getDataFolder(), name + ".yml");
        if (!this.file.exists()) try { file.createNewFile(); } catch (Exception e) { e.printStackTrace(); } // Create the file if it doesn't exist
        this.config = YamlConfiguration.loadConfiguration(this.file);
        CustomYaml.listeners.add(this);
    }

    /**
     * Returns the File.io of your yml file.
     * @return The file
     */
    public File getFile() {
        return file;
    }

    /**
     * Gets the YamlConfiguration for your use.
     * @return YamlConfiguration of the config file.
     */
    public YamlConfiguration getConfig() {
        return config;
    }

    /**
     * Save the current YamlConfiguration to the File.io
     * {@link CustomYaml Do not call unless you set automatically saving to false.}
     */
    public void saveFile() {
        try {
            config.save(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void shutdown() {
        saveFile();
    }
}
