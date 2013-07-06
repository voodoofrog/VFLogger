package uk.co.forgottendream.VFLogger;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

public class VFLogger {
	
	private final Plugin plugin;
	private String pluginDataFolder;
	private final String DATE_FORMAT_NOW = "yyyy-mm-dd HH:mm:ss";
	
    public VFLogger(String pluginName) {
    	this.plugin = Bukkit.getServer().getPluginManager().getPlugin(pluginName);
    	pluginDataFolder = plugin.getDataFolder().getAbsolutePath() + File.separator;
    }
	
	private void addEntry(String level, String message, String path, String logName) {
		File logFile = new File(path + logName + ".log");
		try {
			FileWriter outfile = new FileWriter(logFile, true);
			PrintWriter out = new PrintWriter(outfile);
			logFile.createNewFile();
			out.println(getTimestamp() + " [" + level + "] " + message);
			out.close();
		} catch (IOException e) {
			plugin.getLogger().warning("Could not create " + logName + ".log");
		}
	}
	
	private void addEntry(String level, String message) {
		String logName = plugin.getName();
		String path = pluginDataFolder;
		File logFile = new File(path + logName + ".log");
		try {
			FileWriter outfile = new FileWriter(logFile, true);
			PrintWriter out = new PrintWriter(outfile);
			logFile.createNewFile();
			out.println(getTimestamp() + " [" + level + "] " + message);
			out.close();
		} catch (IOException e) {
			plugin.getLogger().warning("Could not create " + logName + ".log");
		}
	}
	
    /**
     * Adds a warning entry to a specified log
     *
     * @param message The message to log
     * @param path The path to the log file
     * @param logName The name of the log file
     */
	public void warning(String message, String path, String logName) {
		addEntry("WARNING", message, path, logName);
	}
	
    /**
     * Adds a warning entry to the default plugin log
     *
     * @param message The message to log
     */
	public void warning(String message) {
		addEntry("WARNING", message);
	}
	
    /**
     * Adds an info entry to a specified log
     *
     * @param message The message to log
     * @param path The path to the log file
     * @param logName The name of the log file
     */
	public void info(String message, String path, String logName) {
		addEntry("INFO", message, path, logName);
	}
	
    /**
     * Adds an info entry to the default plugin log
     *
     * @param message The message to log
     */
	public void info(String message) {
		addEntry("INFO", message);
	}
	
	private String getTimestamp() {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
		return sdf.format(cal.getTime());
	}
	
	public String getExceptionAsString(Exception exception) {
		  StringWriter sw = new StringWriter();
		  exception.printStackTrace(new PrintWriter(sw));
		  return sw.toString();
	}

}
