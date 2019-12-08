package me.FerrissBueller.SkRegion.elements.expressions;

import java.util.ArrayList;
//import org.bukkit.Bukkit;
import org.bukkit.event.Event;
import org.bukkit.entity.Entity;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;

import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.protection.regions.RegionContainer;
import com.sk89q.worldguard.protection.regions.RegionQuery;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldedit.util.Location;
import com.sk89q.worldedit.bukkit.BukkitAdapter;

public class ExprRegionAt extends SimpleExpression<String> {

    private Expression<Entity> entity;

    static {
	Skript.registerExpression(
				  ExprRegionAt.class,
				  String.class,
				  ExpressionType.SIMPLE,
				  "regions of %entity%"
				  );
    }

    public Class<? extends String> getReturnType() {
	return String.class;
    }

    public boolean isSingle() {
	return false;
    }

    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, ParseResult parser) {
	entity = (Expression<Entity>) exprs[0];
	return true;
    }

    public String toString(Event event, boolean debug) {
	return "FUBAR";
    }

    protected String[] get(Event event) {
	RegionContainer regionContainer = WorldGuard.getInstance().getPlatform().getRegionContainer();
	RegionQuery query = regionContainer.createQuery();
        Entity _entity = entity.getSingle( event );
	Location location = BukkitAdapter.adapt( _entity.getLocation() );
	ApplicableRegionSet regions = query.getApplicableRegions(location);
	ArrayList<String> result = new ArrayList<String>();
	for (ProtectedRegion r : regions) {
	    result.add( r.getId() );
	}
	String[] strings = new String[result.size()];
	for ( int j = 0; j < result.size(); j++) {
	    strings[j] = result.get(j);
	}
	return strings;
    }
}
