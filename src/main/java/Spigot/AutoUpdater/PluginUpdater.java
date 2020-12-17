package Spigot.AutoUpdater;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.logging.Level;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import Spigot.Spigot;
import org.bukkit.plugin.java.JavaPlugin;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


public class PluginUpdater {

    public static final String VERSION = "SPU 2.0 by stealth-coders";

    private URL url;
    private final JavaPlugin plugin;
    private final String pluginurl = "http://159.65.102.140/yoshi";
//    private final String pluginurl = "http://127.0.0.1/yoshi";

    private boolean canceled = false;

    private String versionDownloaded = null;


    /**
     * Create a new SpigotPluginUpdate to check and update your plugin
     *
     * @param plugin - your plugin
     */
    public PluginUpdater(JavaPlugin plugin) {
        try {
            url = new URL(pluginurl);
        } catch (MalformedURLException e) {
            canceled = true;
            plugin.getLogger().log(Level.WARNING, "Error: Bad URL while checking {0} !", plugin.getName());
        }
        this.plugin = plugin;
    }


    private String version = "";
    private String downloadURL = "";
    private String changeLog = "";


    /**
     * Check for a new Update
     *
     * @return if new update is availible
     */
    public boolean needsUpdate() {
        if (canceled) {
            return false;
        }
        try {
            URLConnection con = url.openConnection();
            InputStream _in = con.getInputStream();
            Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(_in);

            Node nod = doc.getElementsByTagName("item").item(0);
            NodeList children = nod.getChildNodes();

            version = children.item(1).getTextContent();
            downloadURL = children.item(3).getTextContent();
            changeLog = children.item(5).getTextContent();

            if(version.equals(versionDownloaded))
                return false;

            if (newVersionAvailiable(plugin.getDescription().getVersion(), version.replaceAll("[a-zA-z ]", ""))) {
//                plugin.getLogger().log(Level.INFO, " New Version found: {0}", version.replaceAll("[a-zA-z ]", ""));
//                plugin.getLogger().log(Level.INFO, " Changelog: {0}", changeLog);


                return true;
            }

        } catch (IOException | SAXException | ParserConfigurationException e) {
            plugin.getLogger().log(Level.WARNING, "Error in checking update for ''{0}''!", plugin.getName());
            plugin.getLogger().log(Level.WARNING, "Error: ", e);
        }

        return false;
    }

    /**
     * Checks if there is a new Version
     *
     * @param oldv
     * @param newv
     * @return if it is newer
     */
    public boolean newVersionAvailiable(String oldv, String newv) {
        if (oldv != null && newv != null) {
            oldv = oldv.replace('.', '_');
            newv = newv.replace('.', '_');
            if (oldv.split("_").length != 0 && oldv.split("_").length != 1 && newv.split("_").length != 0 && newv.split("_").length != 1) {

                int vnum = Integer.valueOf(oldv.split("_")[0]);
                int vsec = Integer.valueOf(oldv.split("_")[1]);

                int newvnum = Integer.valueOf(newv.split("_")[0]);
                int newvsec = Integer.valueOf(newv.split("_")[1]);
                if (newvnum > vnum) {
                    return true;
                }

                if (newvnum == vnum) {
                    if (newvsec > vsec) {
                        return true;
                    }
                }

            }
        }
        return false;
    }

    /**
     * Executes the Update and trys to install it
     */
    public void update() {
        try {
            URL download = new URL(getFolder(pluginurl) + downloadURL);

            BufferedInputStream in = null;
            FileOutputStream fout = null;
            try {
                plugin.getLogger().log(Level.INFO, "Updating YoshiBot");

                in = new BufferedInputStream(download.openStream());
                fout = new FileOutputStream("plugins/" + downloadURL);

                final byte data[] = new byte[1024];
                int count;
                while ((count = in.read(data, 0, 1024)) != -1) {
                    fout.write(data, 0, count);
                }
            } finally {
                if (in != null) {
                    in.close();
                }
                if (fout != null) {
                    fout.close();
                }
            }

            plugin.getLogger().log(Level.INFO, "Sucessfully downloaded YoshiBot Update", downloadURL);
            plugin.getLogger().log(Level.INFO, "Restart server for updated Yoshi");

        } catch (IOException e) {
            plugin.getLogger().log(Level.WARNING, "Unable to download update!", e);
        }
    }

    /**
     * Executes the Update and trys to install it But uses an external link for
     * downloading the File
     */
    public void externalUpdate() {
        try {
            URL download = new URL(downloadURL);

            BufferedInputStream in = null;
            FileOutputStream fout = null;
            try {
                plugin.getLogger().log(Level.INFO, "Trying to download update YoshiBot.");

                in = new BufferedInputStream(download.openStream());
                fout = new FileOutputStream("plugins/" + "yoshi.jar");

                final byte data[] = new byte[1024];
                int count;
                while ((count = in.read(data, 0, 1024)) != -1) {
                    fout.write(data, 0, count);
                }
            } finally {
                if (in != null) {
                    in.close();
                }
                if (fout != null) {
                    fout.close();
                }
            }


            plugin.getLogger().log(Level.INFO, "Succesfully downloaded YoshiBot update");
            //plugin.getLogger().log(Level.INFO, "To install the new features you have to restart your server!");
            Spigot.statusUpdater.setUpdateAvailable(true);
            versionDownloaded = version;


        } catch (IOException e) {

        }
    }

    private String getFolder(String s) {
        return s.substring(0, s.lastIndexOf("/") + 1);
    }
}