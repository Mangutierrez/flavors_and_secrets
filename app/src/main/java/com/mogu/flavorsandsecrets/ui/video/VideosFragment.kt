package com.mogu.flavorsandsecrets.ui.video

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mogu.flavorsandsecrets.MainActivity
import com.mogu.flavorsandsecrets.R
import com.mogu.flavorsandsecrets.VideoPlayerActivity
import com.mogu.flavorsandsecrets.data.DataDummy
import com.mogu.flavorsandsecrets.databinding.FragmentVideoBinding

class VideosFragment : Fragment() {

    private var _binding: FragmentVideoBinding? = null
    private val binding get() = _binding!!
    private val videosAdapter by lazy { VideosAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentVideoBinding.inflate(inflater, container, false)
        (activity as? MainActivity)?.appBarMain?.tvAppBarTitle?.text = "Videos"
        (activity as? MainActivity)?.appBarMain?.tvAppBarSubtitle?.text = ""
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // RecyclerView con un GridLayout para vista cuadriculada
        binding.rvVideos.apply {
            layoutManager = GridLayoutManager(context, 3)
            adapter = videosAdapter
        }

        // Cargar datos en el adaptador
        videosAdapter.submitList(getVideos())
    }

    private fun getVideos(): List<String> {
        return DataDummy.getVideoList()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

class VideosAdapter : RecyclerView.Adapter<VideosAdapter.PhotoViewHolder>() {

    private var videoUrls = emptyList<String>()

    fun submitList(urls: List<String>) {
        videoUrls = urls
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_video, parent, false)
        return PhotoViewHolder(view)
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        val videoUrl = videoUrls[position]
        Glide.with(holder.itemView.context)
            .load(videoUrl)
            .centerCrop()
            .into(holder.imgVideo)

        holder.itemView.setOnClickListener {
            openVideoPlayer(holder.itemView.context, videoUrl)
        }

    }

    override fun getItemCount() = videoUrls.size

    class PhotoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgVideo: ImageView = itemView.findViewById(R.id.imgVideo)
    }

    private fun openVideoPlayer(context: Context, videoUrl: String) {
        val intent = Intent(context, VideoPlayerActivity::class.java)
        intent.putExtra("VIDEO_URL", videoUrl)
        context.startActivity(intent)
    }

}
