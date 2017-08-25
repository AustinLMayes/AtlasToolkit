package net.avicus.toolkit.commands;

import com.sk89q.minecraft.util.commands.Command;
import com.sk89q.minecraft.util.commands.CommandContext;
import com.sk89q.minecraft.util.commands.CommandException;
import net.avicus.compendium.Paste;
import net.avicus.toolkit.utils.XMLInventoryUtils;
import org.apache.commons.lang.Validate;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class XMLCommands {

  @Command(aliases = {
      "inventory"}, desc = "Generate xml for your current inventory", min = 0, max = 0)
  public static void inventory(CommandContext args, CommandSender sender) throws CommandException {
    Validate.isTrue(sender instanceof Player, "Consoles don't have an inventory, silly!");
    Player player = (Player) sender;
    try {
      String link = new Paste("Inventory Dump", "ToolKit", XMLInventoryUtils.inventoryToXML(player))
          .upload();
      player.sendMessage(ChatColor.GREEN + "Upload successful. " + ChatColor.WHITE
          + "Click on this link to view the XML: " + link);
    } catch (Exception e) {
      player.sendMessage(ChatColor.RED
          + "Something went wrong trying to upload the xml to DPaste. Please try again.");
      e.printStackTrace();
    }
  }

  @Command(aliases = {
      "item"}, desc = "Create an item tag for the item you are currently holding", min = 0, max = 0)
  public static void item(CommandContext args, CommandSender sender) throws CommandException {
    Validate.isTrue(sender instanceof Player, "Consoles don't have an inventory, silly!");
    Player player = (Player) sender;
    Validate.isTrue(player.getItemInHand() != null, "Please hold an item in your hand");
    try {
      String link = new Paste("Item", "Toolkit",
          XMLInventoryUtils.itemToXML(player.getItemInHand(), -1, "item")).upload();
      player.sendMessage(ChatColor.GREEN + "Upload successful. " + ChatColor.WHITE
          + "Click on this link to view the XML: " + link);
    } catch (Exception e) {
      player.sendMessage(ChatColor.RED
          + "Something went wrong trying to upload the xml to DPaste. Please try again.");
      e.printStackTrace();
    }
  }
}
