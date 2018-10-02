package me.alfredobejarano.gridimages.repository

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import me.alfredobejarano.gridimages.R
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.io.StringWriter

/**
 * Class that retrieves a list of picture URLs
 * from a JSON file found in the raw resource folder.
 *
 * @author Alfredo Bejarano
 * @version 1.0
 * @since 02/10/2018 - 02:22 PM
 */
class URLsRepository {
    /**
     * Reads the JSON file that contains a list of pictures URLs.
     * @param ctx Context reference, this allows this function to access the raw folder.
     * @return List of URLS as Strings.
     * @see List
     * @see Context
     */
    fun fetchPicturesURLs(ctx: Context): List<String> = try {
        // Create a new StringWriter for building the JSON file.
        val writer = StringWriter()
        // Obtain the file from the RAW folder as an InputStream.
        val resource = ctx.resources.openRawResource(R.raw.images)
        // Now read the file using UTF-8 encoding.
        val reader = BufferedReader(InputStreamReader(resource, "UTF-8"))
        // Read the current line of the reader.
        var line = reader.readLine()
        // Execute this with every line if it is not null.
        while (line != null) {
            // Write the line using the writer.
            writer.write(line)
            // Now, read the next line.
            line = reader.readLine()
        }
        // Create a new GSON instance.
        val gson = Gson()
        // Define the type for a List of strings.
        val stringListType = object : TypeToken<List<String>>() {}.type
        // Set the value for the results and return the object.
        gson.fromJson<List<String>>(writer.toString(), stringListType)
    } catch (e: IOException) {
        // If an IOException is caught, return an empty MutableLiveData object.
        listOf()
    }
}