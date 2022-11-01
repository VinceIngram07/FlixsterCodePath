package com.example.flixster

import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup
import android.widget.ImageView
import com.example.flixster.R.id
//import com.example.flixster.Glide
import kotlinx.coroutines.NonDisposableHandle.parent

class LatestMoviesRecyclerViewAdapter (
    private val movies : List<LatestMovie>,
    private val mListener : OnListFragmentInteractionListener?
    )
: RecyclerView.Adapter<LatestMoviesRecyclerViewAdapter.BookViewHolder>()
    {
            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.fragment_latest_movie, parent, false)
            return BookViewHolder(view)
    }

/**
 * This inner class lets us refer to all the different View elements
 * (Yes, the same ones as in the XML layout files!)
 */
inner class BookViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
    var mItem: LatestMovie? = null
    val mMovieTitle: TextView = mView.findViewById<View>(id.movie_title) as TextView
    val mMovieImage: ImageView = mView.findViewById<View>(id.movie_image) as ImageView
    val mMovieDescription: TextView = mView.findViewById<View>(id.movie_description) as TextView

    override fun toString(): String {
        return mMovieTitle.toString() + " '" + mMovieDescription.text + "'"
    }
}

/**
 * This lets us "bind" each Views in the ViewHolder to its' actual data!
 */
override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
    val movie = movies[position]
    val fullMovieImageUrl = "https://image.tmdb.org/t/p/w500/"

    holder.mItem = movie
    holder.mMovieTitle.text = movie.title
    holder.mMovieDescription.text = movie.description

//    Glide.with(holder.mView)
//        .load(fullMovieImageUrl)
//        .centerInside()
//        .into(holder.mMovieImage)

    holder.mView.setOnClickListener {
        holder.mItem?.let { book ->
            mListener?.onItemClick(book)
        }
    }
}

/**
 * Remember: RecyclerView adapters require a getItemCount() method.
 */
override fun getItemCount(): Int {
    return movies.size
}
}