package edu.unca.jderbysh.PluginTwo;

import org.bukkit.plugin.java.JavaPlugin;

/*
 * This is the main class of the sample plug-in
 */
public final class PluginTwo extends JavaPlugin {
    /*
     * This is called when your plug-in is enabled
     */
	
	PluginTwoLogger logger;
	
    @Override
    public void onEnable() {
    	//create a logger and use it
    	logger = new PluginTwoLogger(this);
    	logger.info("plugin enabled!!");
    	
        // save the configuration file
        saveDefaultConfig();
        
        // Create the SampleListener
        new PluginTwoListener(this);
        
        // set the command executor for sample
        this.getCommand("plugintwo").setExecutor(new PluginTwoCommandExecutor(this));
    }
    
    /*
     * This is called when your plug-in shuts down
     */
    @Override
    public void onDisable() {
        
    }

}
