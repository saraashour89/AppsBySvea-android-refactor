// Generated by Dagger (https://google.github.io/dagger).
package com.close.svea.refactoringsample.DI;

import com.close.svea.refactoringsample.data.network.PlacesApiService;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
import retrofit2.Retrofit;

public final class NetworkModule_ProvidePlaceServiceFactory implements Factory<PlacesApiService> {
  private final NetworkModule module;

  private final Provider<Retrofit> retrofitProvider;

  public NetworkModule_ProvidePlaceServiceFactory(
      NetworkModule module, Provider<Retrofit> retrofitProvider) {
    this.module = module;
    this.retrofitProvider = retrofitProvider;
  }

  @Override
  public PlacesApiService get() {
    return provideInstance(module, retrofitProvider);
  }

  public static PlacesApiService provideInstance(
      NetworkModule module, Provider<Retrofit> retrofitProvider) {
    return proxyProvidePlaceService(module, retrofitProvider.get());
  }

  public static NetworkModule_ProvidePlaceServiceFactory create(
      NetworkModule module, Provider<Retrofit> retrofitProvider) {
    return new NetworkModule_ProvidePlaceServiceFactory(module, retrofitProvider);
  }

  public static PlacesApiService proxyProvidePlaceService(
      NetworkModule instance, Retrofit retrofit) {
    return Preconditions.checkNotNull(
        instance.providePlaceService(retrofit),
        "Cannot return null from a non-@Nullable @Provides method");
  }
}
