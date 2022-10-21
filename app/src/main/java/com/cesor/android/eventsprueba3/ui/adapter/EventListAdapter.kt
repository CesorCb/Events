package com.cesor.android.eventsprueba3.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.cesor.android.eventsprueba3.R
import com.cesor.android.eventsprueba3.databinding.EventItemBinding
import com.cesor.android.eventsprueba3.domain.Event

/****
 * Project: EventsPrueba3
 * From: com.cesor.android.eventsprueba3.ui
 * Created by: César Castro on 14/10/2022 at 19:54.
 ***/
class EventListAdapter(private val listener: OnClickListener) :
    ListAdapter<Event, EventListAdapter.EventViewHolder>(EventDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return EventViewHolder(layoutInflater.inflate(R.layout.event_item, parent, false))
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        val event = getItem(position)
        with(holder) {
            setListener(event)
            binding.tvEventTitle.text = event.name
            binding.tvEventDescription.text = event.description
            binding.tvDate.text = event.date
            binding.imgEventImage.setImageURI(event.photoUrl.toUri())
            /*Glide.with(binding.root)
                .load(event.photoUrl.toUri())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .into(binding.imgEventImage)*/
        }
    }

    inner class EventViewHolder(view: View) : ViewHolder(view) {
        val binding = EventItemBinding.bind(view)

        fun setListener(event: Event) {
            binding.imgBtnEdit.setOnClickListener { listener.onClick(event) }
        }
    }

    class EventDiffCallback : DiffUtil.ItemCallback<Event>() {
        override fun areItemsTheSame(oldItem: Event, newItem: Event): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Event, newItem: Event): Boolean =
            oldItem == newItem
    }
}