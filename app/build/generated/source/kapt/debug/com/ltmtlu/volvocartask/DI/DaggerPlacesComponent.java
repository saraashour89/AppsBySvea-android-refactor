// Generated by Dagger (https://google.github.io/dagger).
package com.ltmtlu.volvocartask.DI;

import com.close.svea.refactoringsample.DI.ContextModule;
import com.close.svea.refactoringsample.DI.ContextModule_ProvideContextFactory;
import com.close.svea.refactoringsample.DI.NetworkModule;
import com.close.svea.refactoringsample.DI.NetworkModule_ProvideCacheFactory;
import com.close.svea.refactoringsample.DI.NetworkModule_ProvideHttpClientFactory;
import com.close.svea.refactoringsample.DI.NetworkModule_ProvidePlaceServiceFactory;
import com.close.svea.refactoringsample.DI.NetworkModule_ProvideRetrofitFactory;
import com.close.svea.refactoringsample.DI.ShowPlacesViewModelModule;
import com.close.svea.refactoringsample.DI.ShowPlacesViewModelModule_ProvidePlaceFactoryFactory;
import com.close.svea.refactoringsample.DI.ShowPlacesViewModelModule_ProvideShowPlaceViewModelFactory;
import com.close.svea.refactoringsample.data.network.PlacesApiService;
import com.close.svea.refactoringsample.data.repository.PlaceRepositoryImpl;
import com.close.svea.refactoringsample.ui.ShowPlacesActivity;
import com.close.svea.refactoringsample.ui.ShowPlacesActivity_MembersInjector;
import com.close.svea.refactoringsample.ui.ShowPlacesViewModel;
import com.close.svea.refactoringsample.ui.item.ShowPlacesViewModelFactory;
import dagger.internal.DoubleCheck;
import dagger.internal.Preconditions;
import javax.inject.Provider;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

public final class DaggerPlacesComponent implements PlacesComponent {
  private ContextModule contextModule;

  private PlaceRepoModule placeRepoModule;

  private ShowPlacesViewModelModule showPlacesViewModelModule;

  private ContextModule_ProvideContextFactory provideContextProvider;

  private NetworkModule_ProvideCacheFactory provideCacheProvider;

  private Provider<OkHttpClient> provideHttpClientProvider;

  private Provider<Retrofit> provideRetrofitProvider;

  private Provider<PlacesApiService> providePlaceServiceProvider;

  private DaggerPlacesComponent(Builder builder) {
    this.contextModule = builder.contextModule;
    this.placeRepoModule = builder.placeRepoModule;
    this.showPlacesViewModelModule = builder.showPlacesViewModelModule;
    initialize(builder);
  }

  public static Builder builder() {
    return new Builder();
  }

  private PlaceRepositoryImpl getPlaceRepositoryImpl() {
    return PlaceRepoModule_ProvidePlaceRepoFactory.proxyProvidePlaceRepo(
        placeRepoModule, providePlaceServiceProvider.get());
  }

  private ShowPlacesViewModelFactory getShowPlacesViewModelFactory() {
    return ShowPlacesViewModelModule_ProvidePlaceFactoryFactory.proxyProvidePlaceFactory(
        showPlacesViewModelModule,
        ContextModule_ProvideContextFactory.proxyProvideContext(contextModule),
        getPlaceRepositoryImpl());
  }

  private ShowPlacesViewModel getShowPlacesViewModel() {
    return ShowPlacesViewModelModule_ProvideShowPlaceViewModelFactory
        .proxyProvideShowPlaceViewModel(showPlacesViewModelModule, getShowPlacesViewModelFactory());
  }

  @SuppressWarnings("unchecked")
  private void initialize(final Builder builder) {
    this.provideContextProvider = ContextModule_ProvideContextFactory.create(builder.contextModule);
    this.provideCacheProvider =
        NetworkModule_ProvideCacheFactory.create(builder.networkModule, provideContextProvider);
    this.provideHttpClientProvider =
        DoubleCheck.provider(
            NetworkModule_ProvideHttpClientFactory.create(
                builder.networkModule, provideCacheProvider));
    this.provideRetrofitProvider =
        DoubleCheck.provider(
            NetworkModule_ProvideRetrofitFactory.create(
                builder.networkModule, provideHttpClientProvider));
    this.providePlaceServiceProvider =
        DoubleCheck.provider(
            NetworkModule_ProvidePlaceServiceFactory.create(
                builder.networkModule, provideRetrofitProvider));
  }

  @Override
  public void inject(ShowPlacesActivity placesActivity) {
    injectShowPlacesActivity(placesActivity);
  }

  private ShowPlacesActivity injectShowPlacesActivity(ShowPlacesActivity instance) {
    ShowPlacesActivity_MembersInjector.injectViewModel(instance, getShowPlacesViewModel());
    return instance;
  }

  public static final class Builder {
    private ShowPlacesViewModelModule showPlacesViewModelModule;

    private ContextModule contextModule;

    private PlaceRepoModule placeRepoModule;

    private NetworkModule networkModule;

    private Builder() {}

    public PlacesComponent build() {
      Preconditions.checkBuilderRequirement(
          showPlacesViewModelModule, ShowPlacesViewModelModule.class);
      Preconditions.checkBuilderRequirement(contextModule, ContextModule.class);
      if (placeRepoModule == null) {
        this.placeRepoModule = new PlaceRepoModule();
      }
      if (networkModule == null) {
        this.networkModule = new NetworkModule();
      }
      return new DaggerPlacesComponent(this);
    }

    public Builder showPlacesViewModelModule(ShowPlacesViewModelModule showPlacesViewModelModule) {
      this.showPlacesViewModelModule = Preconditions.checkNotNull(showPlacesViewModelModule);
      return this;
    }

    public Builder placeRepoModule(PlaceRepoModule placeRepoModule) {
      this.placeRepoModule = Preconditions.checkNotNull(placeRepoModule);
      return this;
    }

    public Builder networkModule(NetworkModule networkModule) {
      this.networkModule = Preconditions.checkNotNull(networkModule);
      return this;
    }

    public Builder contextModule(ContextModule contextModule) {
      this.contextModule = Preconditions.checkNotNull(contextModule);
      return this;
    }
  }
}
