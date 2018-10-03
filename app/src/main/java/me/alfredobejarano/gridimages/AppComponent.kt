package me.alfredobejarano.gridimages

import dagger.Component
import dagger.Module
import dagger.Provides
import dagger.android.support.AndroidSupportInjectionModule
import me.alfredobejarano.gridimages.repository.URLsRepository
import me.alfredobejarano.gridimages.viewmodel.URLsViewModel
import javax.inject.Singleton

/**
 * Dagger component that allows injecting dependencies through the app.
 *
 * @author Alfredo Bejarano
 * @version 1.0
 * @since 02/10/2018 - 06:28 PM
 */
@Singleton
@Component(modules = [(AndroidSupportInjectionModule::class), (RepositoryModule::class)])
interface AppComponent {
    /**
     * Inject function for URLsViewModel.
     */
    fun inject(viewModel: URLsViewModel)
}

/**
 * Dagger module that defines how to inject a URLsRepository class.
 *
 * @author Alfredo Bejarano
 * @version 1.0
 * @since 02/10/2018 - 07:12 PM
 */
@Module
class RepositoryModule {
    @Singleton
    @Provides
    internal fun provideUrlsRepository() = URLsRepository()
}