package black.bracken.papertemplate.listener;

import black.bracken.papertemplate.usecase.SampleUsecase;
import black.bracken.papertemplate.util.PaperScheduler;
import com.google.inject.Inject;
import io.reactivex.rxjava3.core.Observable;
import io.vavr.collection.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import lombok.val;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.Plugin;

public class SampleListener implements Listener {

  @Inject
  private Plugin plugin;

  @Inject
  private SampleUsecase sampleUsecase;

  @EventHandler
  public void onPlayerInteract(PlayerInteractEvent event) {
    val player = event.getPlayer();
    if (
      !player.isOp() || player.getInventory().getItemInMainHand().getType() != Material.STICK || event.getAction() != Action.LEFT_CLICK_AIR
    ) {
      return;
    }

    Observable
      .interval(3, TimeUnit.SECONDS)
      .take(5)
      .observeOn(PaperScheduler.sync(plugin))
      .subscribe(i -> {
        val itemAmount = List
          .of(player.getInventory().getContents())
          .filter(item -> item != null && item.getType() != Material.AIR)
          .map(item -> Objects.requireNonNull(item).getAmount())
          .sum()
          .intValue();

        player.sendMessage("you have " + itemAmount + "items.");
      });
  }
}
