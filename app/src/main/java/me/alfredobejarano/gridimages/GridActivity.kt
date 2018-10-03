package me.alfredobejarano.gridimages

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.annotation.StringRes
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.view.View
import com.facebook.drawee.backends.pipeline.Fresco
import kotlinx.android.synthetic.main.activity_grid.*
import me.alfredobejarano.gridimages.adapter.PicturesAdapter
import me.alfredobejarano.gridimages.viewmodel.URLsViewModel

/**
 * Constant value that defines hoy many columns the grid will have.
 */
private const val GRID_COLUMNS_COUNT = 2

/**
 * [AppCompatActivity] class that displays a grid of pictures using a RecyclerView.
 *
 * @author Alfredo Bejarano
 * @version 1.0
 * @since 02/10/2018 - 05:48 PM
 */
class GridActivity : AppCompatActivity() {
    /**
     * Reference to the ViewModel class for this Activity.
     */
    private lateinit var viewModel: URLsViewModel

    /**
     * Reference to the cached URLs for this activity.
     */
    private var cachedURLs: List<String>? = null

    /**
     * Initializes this activity, sets observers and retrieves the
     * pictures from the ViewModel.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Initialize fresco
        Fresco.initialize(this)
        // Set this activity content view.
        setContentView(R.layout.activity_grid)
        // Retrieve the URLsViewModel object for this activity.
        viewModel = ViewModelProviders.of(this)[URLsViewModel::class.java]
        // Assign a new layout manager to the list if it doesn't has one.
        if (image_grid?.layoutManager == null) {
            image_grid?.layoutManager = GridLayoutManager(this, GRID_COLUMNS_COUNT)
        }
        // If the cachedURLs list is not null or empty, fill the grid using it.
        if (cachedURLs?.isNotEmpty() == true) {
            fillGrid()
        } else { // If the list is null or empty, fetch the pictures from the ViewModel
            // Observe changes in the ViewModel status value.
            observeViewModelStatusChanges()
            // Observe changes in the ViewModel results.
            observeViewModelURLsChanges()
            // Retrieve the pictures.
            viewModel.retrievePictures()
        }
    }

    /**
     * Adds an observer to the ViewModel status property,
     * depending on the changed value it will display
     * or hide the loading view.
     */
    private fun observeViewModelStatusChanges() {
        viewModel.status.observe(this, Observer {
            displayLoadingView(URLsViewModel.Status.STATUS_BUSY == it)
        })
    }

    /**
     * Observes changes in the URL results from the ViewModel.
     */
    private fun observeViewModelURLsChanges() {
        viewModel.urls.observe(this, Observer {
            // Check that the list is not null or empty.
            if (it?.isNotEmpty() == true) {
                // If the list is not empty, Set the result as cached and fill the grid.
                cachedURLs = it
                fillGrid()
            } else {
                displayMessage(R.string.no_pictures_found)
            }
        })
    }

    /**
     * Changes the visibility of the loading view.
     * @param displayView true will display the loading view or false will hide the loading view.
     */
    private fun displayLoadingView(displayView: Boolean) {
        loading_view?.visibility = if (displayView) View.VISIBLE else View.GONE
    }

    /**
     * Displays a [Snackbar] showing a message using a String resource.
     * @param message The string resource for the message.
     */
    private fun displayMessage(@StringRes message: Int) =
            Snackbar.make(findViewById(android.R.id.content), message, Snackbar.LENGTH_SHORT).show()

    /**
     * Removes any adapter from the grid RecyclerView and
     * assigns a new one filling it with the cached URLs.
     */
    private fun fillGrid() {
        // remove any adapter previously assigned.
        image_grid?.adapter = null
        // Assign a new adapter for the grid.
        /**
         * At this point we use the unsafe operator (!!) as this
         * function gets called when the cachedURLs list is not null
         */
        image_grid?.adapter = PicturesAdapter(cachedURLs!!)
    }
}