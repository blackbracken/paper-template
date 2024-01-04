package black.bracken.papertemplate;

import black.bracken.papertemplate.listener.SampleListener;
import com.google.inject.Guice;
import lombok.val;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.java.JavaPlugin;

public final class EntryPoint extends JavaPlugin {

  @Override
  public void onEnable() {
    val injector = Guice.createInjector(new ConcreteModule());

    val pluginManager = getServer().getPluginManager();
    pluginManager.registerEvents(
      injector.getInstance(SampleListener.class),
      this
    );
  }

  @Override
  public void onDisable() {
    HandlerList.unregisterAll(this);
  }

}
