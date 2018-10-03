package me.alfredobejarano.gridimages.repository

import android.app.Application
import android.os.Build
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config

/**
 * Unit test class for the [URLsRepository] class.
 *
 * The configuration for Robolectric uses the default
 * application class and forces it to use API level 27.
 *
 * @author Alfredo Bejarano
 * @version 1.0
 * @since 02/10/2018 - 03:09 PM
 */
@RunWith(RobolectricTestRunner::class)
@Config(application = Application::class, sdk = [Build.VERSION_CODES.O_MR1])
class URLsRepositoryTest {
    /**
     * Tests that reading and retrieving the list of pictures does not fails.
     */
    @Test
    fun fetchPicturesURLs() {
        // Create a new instance from the repository class.
        val testRepository = URLsRepository()
        // Retrieve the pictures from the JSON file, the JSON file contains pictures.
        val results = testRepository.fetchPicturesURLs(RuntimeEnvironment.application)
        // Assert that the results aren't empty.
        assert(results.isNotEmpty())
    }
}