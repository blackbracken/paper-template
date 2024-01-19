package black.bracken.papertemplate;

import black.bracken.papertemplate.listener.SampleListener;
import black.bracken.papertemplate.util.PaperScheduler;
import com.google.inject.Guice;
import com.google.inject.Injector;
import io.reactivex.rxjava3.schedulers.Schedulers;
import lombok.val;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public final class EntryPoint extends JavaPlugin {

  @Override
  public void onEnable() {
    val injector = Guice.createInjector(new ConcreteModule(this));

    registerEvents(injector, SampleListener.class);
  }

  @Override
  public void onDisable() {
    Schedulers.shutdown();
    PaperScheduler.sync(this).shutdown();
    HandlerList.unregisterAll(this);
  }

  private void registerEvents(Injector injector, Class<? extends Listener> clazz) {
    getServer().getPluginManager().registerEvents(injector.getInstance(clazz), this);
  }
}
