package net.avicus.toolkit.commands;

import com.sk89q.minecraft.util.commands.Command;
import com.sk89q.minecraft.util.commands.CommandContext;
import com.sk89q.minecraft.util.commands.CommandException;
import com.sk89q.worldedit.Vector;
import com.sk89q.worldedit.bukkit.WorldEditPlugin;
import com.sk89q.worldedit.bukkit.selections.CuboidSelection;
import com.sk89q.worldedit.bukkit.selections.Selection;
import net.avicus.toolkit.AtlasToolkit;
import net.avicus.toolkit.PlayerManager;
import net.avicus.toolkit.ToolkitPlayer;
import net.avicus.toolkit.utils.XMLUtils;
import org.apache.commons.lang.Validate;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GenerateCommands {
    @Command(aliases = {"point"}, desc = "Generate a point based on where you are currently standing", usage = "[id] - The ID of the region", min = 1, max = 1)
    public static void point(final CommandContext args, CommandSender sender) throws CommandException {
        Validate.isTrue(sender instanceof Player, "This command cannot be run from the console. Get online!");
        org.bukkit.util.Vector v = ((Player) sender).getLocation().toVector();
        ToolkitPlayer p = PlayerManager.getPlayer((Player) sender);

        String str = "<point id=\"" + args.getJoinedStrings(0) + "\">" + XMLUtils.vectorToString(v, true) + "</point>";
        p.addGeneratedStringToCache(str);
        p.getBukkit().sendMessage(ChatColor.GREEN + "Generated region. " + ChatColor.AQUA + "Use " + ChatColor.WHITE + "/toolkit generate upload" + ChatColor.AQUA + " to upload all generated regions and empty the list.");
    }

    @Command(aliases = {"cuboid", "c"}, desc = "Generate a cuboid based on the current WorldEdit selection", usage = "[id] - The ID of the region", min = 1, max = 1)
    public static void cuboid(final CommandContext args, CommandSender sender) throws CommandException {
        Validate.isTrue(sender instanceof Player, "This command cannot be run from the console. Get online!");
        WorldEditPlugin we = AtlasToolkit.getWorldEdit();
        ToolkitPlayer p = PlayerManager.getPlayer((Player) sender);
        Selection s = we.getSelection(p.getBukkit());
        Validate.notNull(s, "Please make a selection first!");
        Validate.isTrue(s instanceof CuboidSelection, "Non-cuboid selections are not implemented (yet).");
        CuboidSelection selection = (CuboidSelection) s;
        Vector min = selection.getNativeMinimumPoint();
        Vector max = selection.getNativeMaximumPoint();
        String str = "<cuboid id=\"" + args.getJoinedStrings(0) + "\" min=\"" + XMLUtils.weVectorToString(min, false) + "\" max=\"" + XMLUtils.weVectorToString(max, false) + "\" />";
        p.addGeneratedStringToCache(str);
        p.getBukkit().sendMessage(ChatColor.GREEN + "Generated region. " + ChatColor.AQUA + "Use " + ChatColor.WHITE + "/toolkit generate upload" + ChatColor.AQUA + " to upload all generated regions and empty the list.");
    }

    @Command(aliases = {"circle", "ci"}, desc = "Generate a circle based on your current position", usage = "[radius] - The radius of the circle, [id] - The ID of the region", min = 2, max = 2)
    public static void circle(final CommandContext args, CommandSender sender) throws CommandException {
        Validate.isTrue(sender instanceof Player, "This command cannot be run from the console. Get online!");
        ToolkitPlayer p = PlayerManager.getPlayer((Player) sender);
        int radius = args.getInteger(0);
        String id = args.getJoinedStrings(1);
        String str = "<circle id=\"" + id + "\" center=\"" + XMLUtils.vector2DToString(p.getBukkit().getLocation().toVector(), false) + "\" radius=\"" + radius + "\" />";
        p.addGeneratedStringToCache(str);
        p.getBukkit().sendMessage(ChatColor.GREEN + "Generated region. " + ChatColor.AQUA + "Use " + ChatColor.WHITE + "/toolkit generate upload" + ChatColor.AQUA + " to upload all generated regions and empty the list.");
    }

    @Command(aliases = {"cylinder", "cy"}, desc = "Generate a cylinder based on your current position", usage = "[radius] - The radius of the circle, [height] - The height of the cylinder, [id] - The ID of the region", min = 3, max = 3)
    public static void cylinder(final CommandContext args, CommandSender sender) throws CommandException {
        Validate.isTrue(sender instanceof Player, "This command cannot be run from the console. Get online!");
        ToolkitPlayer p = PlayerManager.getPlayer((Player) sender);
        int radius = args.getInteger(0);
        int height = args.getInteger(1);
        String id = args.getJoinedStrings(2);
        String str = "<cylinder id=\"" + id + "\" base=\"" + XMLUtils.vectorToString(p.getBukkit().getLocation().toVector(), false) + "\" radius=\"" + radius + "\" height=\"" + height + "\" />";
        p.addGeneratedStringToCache(str);
        p.getBukkit().sendMessage(ChatColor.GREEN + "Generated region. " + ChatColor.AQUA + "Use " + ChatColor.WHITE + "/toolkit generate upload" + ChatColor.AQUA + " to upload all generated regions and empty the list.");
    }

    @Command(aliases = {"sphere", "s"}, desc = "Generate a sphere based on your current position", usage = "[radius] - The radius of the sphere, [id] - The id of the region", min = 2, max = 2)
    public static void sphere(final CommandContext args, CommandSender sender) throws CommandException {
        Validate.isTrue(sender instanceof Player, "This command cannot be run from the console. Get online!");
        ToolkitPlayer p = PlayerManager.getPlayer((Player) sender);
        int radius = args.getInteger(0);
        String id = args.getJoinedStrings(2);
        String str = "<sphere id=\"" + id + "\" origin=\"" + XMLUtils.vectorToString(p.getBukkit().getLocation().toVector(), false) + "\" radius=\"" + radius + "\" />";
        p.addGeneratedStringToCache(str);
        p.getBukkit().sendMessage(ChatColor.GREEN + "Generated region. " + ChatColor.AQUA + "Use " + ChatColor.WHITE + "/toolkit generate upload" + ChatColor.AQUA + " to upload all generated regions and empty the list.");
    }


    @Command(aliases = {"upload", "u"}, desc = "Upload all generated regions to DPaste", min = 0, max = 0)
    public static void upload(final CommandContext args, CommandSender sender) throws CommandException {
        Validate.isTrue(sender instanceof Player, "This command cannot be run from the console. Get online!");
        ToolkitPlayer p = PlayerManager.getPlayer((Player) sender);
        p.getBukkit().sendMessage(p.uploadGeneratedStringCache());
    }
}
