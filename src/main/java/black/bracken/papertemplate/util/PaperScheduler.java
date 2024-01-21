package black.bracken.papertemplate.util;

import com.google.common.annotations.VisibleForTesting;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import java.util.concurrent.TimeUnit;
import lombok.val;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.scheduler.BukkitTask;

public final class PaperScheduler extends Scheduler {

  private final BukkitScheduler scheduler;
  private final Plugin plugin;

  public static PaperScheduler sync(Plugin plugin) {
    return new PaperScheduler(plugin.getServer().getScheduler(), plugin);
  }

  private PaperScheduler(BukkitScheduler scheduler, Plugin plugin) {
    this.scheduler = scheduler;
    this.plugin = plugin;
  }

  @Override
  public Worker createWorker() {
    return new PaperWorker(scheduler, plugin);
  }

  @Override
  public void shutdown() {
    scheduler.cancelTasks(plugin);
  }

  @VisibleForTesting
  public static final class PaperWorker extends Worker {

    private static final long TICKS_PER_SECOND = 20;

    private final BukkitScheduler scheduler;
    private final Plugin plugin;
    private final CompositeDisposable compositeDisposable = new CompositeDisposable();

    private PaperWorker(BukkitScheduler scheduler, Plugin plugin) {
      this.scheduler = scheduler;
      this.plugin = plugin;
    }

    @Override
    public void dispose() {
      compositeDisposable.dispose();
    }

    @Override
    public boolean isDisposed() {
      return compositeDisposable.isDisposed();
    }

    @Override
    public Disposable schedule(Runnable run) {
      val disposable = new BukkitTaskDisposable(scheduler.runTask(plugin, run));
      compositeDisposable.add(disposable);
      return disposable;
    }

    @Override
    public Disposable schedule(Runnable run, long delay, TimeUnit unit) {
      val disposable = new BukkitTaskDisposable(scheduler.runTask(plugin, run));
      compositeDisposable.add(disposable);
      return disposable;
    }

    @Override
    public Disposable schedulePeriodically(Runnable run, long initialDelay, long period, TimeUnit unit) {
      val task = scheduler.runTaskTimer(plugin, run, convertToTicks(initialDelay, unit), convertToTicks(period, unit));
      val disposable = new BukkitTaskDisposable(task);
      compositeDisposable.add(disposable);
      return disposable;
    }

    @VisibleForTesting
    public static long convertToTicks(long delay, TimeUnit unit) {
      return unit.toMillis(delay) / (1000 / TICKS_PER_SECOND);
    }
  }

  private record BukkitTaskDisposable(BukkitTask bukkitTask) implements Disposable {
    @Override
    public void dispose() {
      bukkitTask.cancel();
    }

    @Override
    public boolean isDisposed() {
      return bukkitTask.isCancelled();
    }
  }
}
