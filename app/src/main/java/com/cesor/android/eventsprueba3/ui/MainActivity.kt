package com.cesor.android.eventsprueba3.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.cesor.android.eventsprueba3.R
import com.cesor.android.eventsprueba3.databinding.ActivityMainBinding
import com.cesor.android.eventsprueba3.domain.Event
import com.cesor.android.eventsprueba3.ui.adapter.EventListAdapter
import com.cesor.android.eventsprueba3.ui.adapter.OnClickListener
import com.cesor.android.eventsprueba3.ui.viewModel.EventViewModel

class MainActivity : AppCompatActivity(), OnClickListener {
    private lateinit var binding: ActivityMainBinding
    private var mAdapter = EventListAdapter(this)
    private val eventViewModel: EventViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        eventViewModel.setEventList()
        setupRecyclerView()
        setupObservers()
        setupButtons()
    }

    private fun setupRecyclerView() {
        binding.recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = mAdapter
        }
    }

    private fun setupObservers() {
        eventViewModel.eventList.observe(this) {
            mAdapter.submitList(it)
        }
    }

    private fun setupButtons() {
        binding.fabAddEvent.setOnClickListener { launchEditFragment() }
        binding.btnHelper.setOnClickListener { eventViewModel.deleteAllEvents() }

    }

    private fun launchEditFragment() {
        val fragment = EventEditFragment()
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.containerMain, fragment, EventEditFragment::class.java.name)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }

    //OnClickListener
    override fun onClick(event: Event) {
        eventViewModel.event.value = event
        Log.i("ayutoo1","$event")
        eventViewModel.isEditMode.value = true
        launchEditFragment()
    }
}
