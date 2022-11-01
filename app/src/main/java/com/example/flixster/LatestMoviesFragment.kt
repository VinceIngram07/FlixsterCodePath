package com.example.flixster


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.ContentLoadingProgressBar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.load.model.Headers
import org.json.JSONArray

// --------------------------------//
// CHANGE THIS TO BE YOUR API KEY  //
// --------------------------------//
private const val API_KEY = "a07e22bc18f5cb106bfe4cc1f83ad8ed"

/*
 * The class for the only fragment in the app, which contains the progress bar,
 * recyclerView, and performs the network calls to the NY Times API.
 */
class LatestMoviesFragment  : Fragment(), OnListFragmentInteractionListener {

    /*
     * Constructing the view
     */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_latest_moives_list, container, false)
        val progressBar = view.findViewById<View>(R.id.progress) as ContentLoadingProgressBar
        val recyclerView = view.findViewById<View>(R.id.list) as RecyclerView
        val context = view.context
        recyclerView.layoutManager = GridLayoutManager(context, 1)
        updateAdapter(progressBar, recyclerView)
        return view
    }

    /*
     * Updates the RecyclerView adapter with new data.  This is where the
     * networking magic happens!
     */
    private fun updateAdapter(progressBar: ContentLoadingProgressBar, recyclerView: RecyclerView) {
        progressBar.show()
        Log.d ("why","update adapter Start")
        // Create and set up an AsyncHTTPClient() here
        val client = AsyncHttpClient()
        // Using the client, perform the HTTP request
        val params = RequestParams()
        /* Uncomment me once you complete the above sections!
            params ["api-key"] = API_KEY
         */
        params["limit"] = "5"
        params["page"] = "1"
        client["https://api.themoviedb.org/3/movie/now_playing?api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed",
        client[
                "https://api.themoviedb.org/3/movie/now_playing?api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed",
                params,
                object : JsonHttpResponseHandler()
//                object : TextHttpResponseHandler()

                {
                    override fun onSuccess(
                        statusCode: Int,
                        headers: Headers,
                        json: JsonHttpResponseHandler.JSON,
                    ) {
                        // The wait for a response is over
                        progressBar.hide()

                        //TODO - Parse JSON into Models
                        Log.d("LatestMovieFragment", "response successful")
                        val resultsJSON: JSONArray = json.jsonObject.get("results") as JSONArray

                        val moviesRawJson: String = resultsJson.toString()

                        val gson = Gson()
                        val arrayMovieType = object : TypeToken<List<latestMovie>>({}.type)

                        Log.d("LatestMoviesFragment", resultsJson.toString())

                        val models: List<BestSellerBook> = null // Fix me!
                        recyclerView.adapter =
                            BestSellerBooksRecyclerViewAdapter(models, this@BestSellerBooksFragment)

                        // Look for this in Logcat:
                        Log.d("BestSellerBooksFragment", "response successful")
                    }

                    /*
             * The onFailure function gets called when
             * HTTP response status is "4XX" (eg. 401, 403, 404)
             */
                    override fun onFailure(
                        statusCode: Int,
                        headers: Headers?,
                        errorResponse: String,
                        t: Throwable?,
                    ) {
                        // The wait for a response is over
                        progressBar.hide()

                        // If the error is not null, log it!
                        t?.message?.let {
                            Log.e("BestSellerBooksFragment", errorResponse)
                        }
                    }
                }

    }

    open class JsonHttpResponseHandler {

    }

    override fun onItemClick(item: LatestMovie) {
        Toast.makeText(context, "test: " + item.title, Toast.LENGTH_LONG).show()
    }

}
