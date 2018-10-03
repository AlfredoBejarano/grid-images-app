package me.alfredobejarano.gridimages.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import me.alfredobejarano.gridimages.DaggerAppComponent
import me.alfredobejarano.gridimages.repository.URLsRepository
import javax.inject.Inject

/**
 * Simple [AndroidViewModel] class that connects the UI
 * with a repository of picture URLs.
 *
 * @author Alfredo Bejarano
 * @version 1.0
 * @since 02/10/2018 - 02:24 PM
 */
class URLsViewModel(application: Application) : AndroidViewModel(application) {
    /**
     * Reference to the URLsRepository class.
     */
    @Inject
    lateinit var urlsRepo: URLsRepository

    /**
     * Property that references the results of a URLs fetching.
     */
    val urls: MutableLiveData<List<String>> = MutableLiveData()

    /**
     * MutableLiveData property that defines the current status of this ViewModel.
     */
    val status: MutableLiveData<Status> = MutableLiveData()

    init {
        // Inject dependencies using the App component.
        DaggerAppComponent.create().inject(this)
    }

    /**
     * Creates a worker thread to read the pictures
     * URL JSON file and retrieve its values.
     */
    fun retrievePictures() {
        // Report to the UI that the ViewModel is doing some work.
        status.value = Status.STATUS_BUSY
        // Report the results from the repo to the LiveData property.
        urls.value = urlsRepo.fetchPicturesURLs(getApplication())
        // Report that the ViewModel has finished doing work.
        status.value = Status.STATUS_READY
    }

    /**
     * Enum class that defines the status values that the ViewModel can be in.
     */
    enum class Status {
        /**
         * Defines that the ViewModel is no longer doing work.
         */
        STATUS_READY,
        /**
         * Defines that the ViewModel is currently doing some work.
         */
        STATUS_BUSY
    }
}