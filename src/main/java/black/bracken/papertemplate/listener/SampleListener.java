package black.bracken.papertemplate.listener;

import black.bracken.papertemplate.usecase.SampleUsecase;
import com.google.inject.Inject;
import io.vavr.collection.List;
import lombok.val;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class SampleListener implements Listener {

  @Inject
  private SampleUsecase sampleUsecase;

  @EventHandler
  public void onPlayerInteract(PlayerInteractEvent event) {
    val player = event.getPlayer();
    if (!player.isOp()
      || player.getInventory().getItemInMainHand().getType() != Material.STICK
      || event.getAction() != Action.LEFT_CLICK_AIR
    ) {
      return;
    }

    val itemAmount = List.of(player.getInventory().getContents())
      .map(item -> item != null && item.getType() != Material.AIR ? item.getAmount() : 0)
      .sum()
      .intValue();

    val text = Component.text()
      .color(NamedTextColor.AQUA)
      .content("You have ")
      .append(Component.text(Integer.toString(itemAmount))
        .color(NamedTextColor.GOLD)
        .decoration(TextDecoration.BOLD, true)
      )
      .append(Component.text(" items! and 2 * " + itemAmount + " = " + sampleUsecase.invoke(itemAmount)))
      .build();

    player.sendMessage(text);
  }

}
