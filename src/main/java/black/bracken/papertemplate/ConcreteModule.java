package black.bracken.papertemplate;

import com.google.inject.AbstractModule;
import org.bukkit.plugin.Plugin;

public final class ConcreteModule extends AbstractModule {

  private final Plugin plugin;

  public ConcreteModule(Plugin plugin) {
    this.plugin = plugin;
  }

  @Override
  protected void configure() {
    bind(Plugin.class).toInstance(plugin);
  }
}
