package black.bracken.papertemplate;

import black.bracken.papertemplate.usecase.SampleUsecase;
import com.google.inject.AbstractModule;

public final class ConcreteModule extends AbstractModule {

  @Override
  protected void configure() {
    bind(SampleUsecase.class).asEagerSingleton();
  }

}
