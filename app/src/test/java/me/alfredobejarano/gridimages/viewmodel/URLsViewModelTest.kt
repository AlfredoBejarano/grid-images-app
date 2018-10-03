package me.alfredobejarano.gridimages.viewmodel

import android.app.Application
import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.arch.lifecycle.Observer
import android.os.Build
import me.alfredobejarano.gridimages.repository.URLsRepository
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config

/**
 * Unit test class for the [URLsViewModel] class.
 *
 * It tests that the methods from the repository class
 * gets called and also checks that a expected list of
 * results matches the one sent by the mocked repository
 * class.
 *
 * @author Alfredo Bejarano
 * @version 1.0
 * @since 02/10/2018 - 04:16 PM
 */
@RunWith(RobolectricTestRunner::class)
@Config(application = Application::class, sdk = [Build.VERSION_CODES.O_MR1])
class URLsViewModelTest {
    /**
     * Rule that allows the calling of LiveData observers in Unit tests.
     */
    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()
    /**
     * Mock repository class, mocked using mockito, allowing spying on it.
     */
    @Mock
    lateinit var urlRepository: URLsRepository
    /**
     * Mock observer, mocking made using mockito allows the spying of its function calls.
     */
    @Mock
    lateinit var observer: Observer<List<String>>
    /**
     * List that contains a single element, this is the expected result.
     */
    private val expectedResults = listOf("http://alfredobejarano.me/images/alfredo.jpg")

    /**
     * Initializes the mocked objects defined by the Mock annotation.
     */
    @Before
    fun initializeMocks() = MockitoAnnotations.initMocks(this)

    /**
     * Asserts that the flow of retrieving a list of picture URLs is
     * executed correctly, mockito verifies that the required methods get
     * called.
     */
    @Test
    fun retrievePictures() {
        // Retrieve the application instance from the Robolectric Runtime.
        val application = RuntimeEnvironment.application
        // Create a new ViewModel object to perform tests on.
        val viewModel = URLsViewModel(urlRepository, application)
        // Observe the viewModel results using our mock observer.
        viewModel.urls.observeForever(observer)
        /**
         * Return the expectedResults list when the mocked repository
         * object gets its fetchPicturesURLs function called.
         */
        Mockito.`when`(urlRepository.fetchPicturesURLs(application))
                .thenReturn(expectedResults)
        // Fetch the pictures
        viewModel.retrievePictures()
        /**
         * Also verify that the mocked observer onChanged function
         * gets called using the expectedResults list as the parameter.
         */
        Mockito.verify(observer).onChanged(expectedResults)
    }

    /**
     * Verifies that mockito has been used in order to finish the test.
     */
    @After
    fun finishMockito() = Mockito.validateMockitoUsage()
}