package net.avicus.toolkit;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import java.util.List;
import net.avicus.compendium.Paste;
import org.apache.commons.lang.Validate;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class ToolkitPlayer {

  private List<String> generatedRegionsCache = Lists.newArrayList();
  private Player bukkit;

  public ToolkitPlayer(Player b) {
    this.bukkit = b;
  }

  public void addGeneratedStringToCache(String str) {
    this.generatedRegionsCache.add(str + "\n\n");
  }

  public String uploadGeneratedStringCache() {
    Validate.isTrue(this.generatedRegionsCache.size() > 0, "Please generate some regions first.");
    try {
      String link = new Paste("Region Cache", "ToolKit",
          Joiner.on("").join(this.generatedRegionsCache)).upload();
      this.generatedRegionsCache.clear();
      return ChatColor.GREEN + "Upload successful. " + ChatColor.WHITE
          + "Click on this link to view the XML: " + link;
    } catch (Exception e) {
      e.printStackTrace();
      return ChatColor.RED
          + "Something went wrong trying to upload the xml to DPaste. Please try again.";
    }
  }

  public Player getBukkit() {
    return bukkit;
  }
}
