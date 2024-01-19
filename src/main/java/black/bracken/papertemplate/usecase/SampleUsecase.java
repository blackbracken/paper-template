package black.bracken.papertemplate.usecase;

import com.google.inject.ImplementedBy;

@ImplementedBy(SampleUsecase.SampleUsecaseImpl.class)
public interface SampleUsecase {
  int invoke(int number);

  final class SampleUsecaseImpl implements SampleUsecase {
    @Override
    public int invoke(int number) {
      return 2 * number;
    }
  }
}
