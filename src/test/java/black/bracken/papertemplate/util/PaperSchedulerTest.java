package black.bracken.papertemplate.util;

import com.google.common.truth.Truth;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.Test;

public class PaperSchedulerTest {

  @Test
  public void testPaperWorkerConvertTicks() {
    Truth.assertThat(PaperScheduler.PaperWorker.convertToTicks(3, TimeUnit.MINUTES))
        .isEqualTo(3600L);
    Truth.assertThat(PaperScheduler.PaperWorker.convertToTicks(3, TimeUnit.SECONDS)).isEqualTo(60L);
    Truth.assertThat(PaperScheduler.PaperWorker.convertToTicks(150, TimeUnit.MILLISECONDS))
        .isEqualTo(3L);
    Truth.assertThat(PaperScheduler.PaperWorker.convertToTicks(0, TimeUnit.MILLISECONDS))
        .isEqualTo(0L);
  }
}
