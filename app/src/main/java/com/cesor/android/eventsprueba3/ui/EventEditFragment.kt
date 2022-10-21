package com.cesor.android.eventsprueba3.ui

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.cesor.android.eventsprueba3.R
import com.cesor.android.eventsprueba3.databinding.FragmentEventEditBinding
import com.cesor.android.eventsprueba3.domain.Event
import com.cesor.android.eventsprueba3.ui.viewModel.EventViewModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder


class EventEditFragment : Fragment() {

    private lateinit var mBinding: FragmentEventEditBinding
    private lateinit var event: Event
    private val eventViewModel: EventViewModel by activityViewModels()
    private var isEditMode = false
    private var mPhotoSelectedUri: Uri? = null
    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) {
            openGallery()
        } else {
            Toast.makeText(
                requireContext(),
                "El permiso no ha sido otorgado",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private val galleryResult = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        if (it.resultCode == Activity.RESULT_OK) {
            mPhotoSelectedUri = it.data?.data
            with(mBinding) {
                Glide.with(mBinding.root)
                    .load(mPhotoSelectedUri)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .centerCrop()
                    .into(imgPhoto)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        mBinding = FragmentEventEditBinding.inflate(layoutInflater, container, false)
        return (mBinding.root)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupButtons()
        setupUiEvents()
        setupObservers()
    }

    private fun setupObservers() {
        eventViewModel.isEditMode.observe(requireActivity()) { isEditMode = it }
    }

    private fun setupButtons() {
        mBinding.btnCreateEvent.setOnClickListener { createEvent() }
        mBinding.imgBtnAddImage.setOnClickListener { checkGalleryPermission() }
        mBinding.btnDelete.setOnClickListener { confirmDelete(event) }
        mBinding.etDate.setOnClickListener { showDatePickerDialog() }
    }

    private fun showDatePickerDialog() {
        val datePicker = DatePickerFragment { day, month, year -> onDateSelected(day, month, year) }
        datePicker.show(parentFragmentManager, "datePicker")
    }

    private fun onDateSelected(day: Int, month: Int, year: Int) {
        mBinding.etDate.setText(getString(R.string.event_date, day, month, year))
    }

    private fun checkGalleryPermission() {
        if (ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.READ_EXTERNAL_STORAGE
            ) == PackageManager.PERMISSION_GRANTED
        ) openGallery() else requestCameraPermission()
    }

    private fun requestCameraPermission() {
        if (shouldShowRequestPermissionRationale(Manifest.permission.READ_EXTERNAL_STORAGE)) {
            requestPermissionLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
        } else {
            requestPermissionLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
        }
    }

    private fun openGallery() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        galleryResult.launch(intent)
    }

    //CREACIÓN DE EVENTOS
    private fun createEvent() {
        val name = mBinding.etEventName.text.toString()
        val description = mBinding.etEventDescription.text.toString()
        val photoUrl = mPhotoSelectedUri
        val date = mBinding.etDate.text.toString()

        if (eventViewModel.isEditMode.value!!) {
            val id = event.id
            event = Event(
                id = id,
                name = name,
                description = description,
                date = date,
                photoUrl = photoUrl.toString()
            )
            Log.i("Ayutoo3", "${event.id}")
            eventViewModel.updateEvent(event)
            Toast.makeText(context, "Se guardaron los cambios del evento", Toast.LENGTH_SHORT)
                .show()
        } else {
            event = Event(
                name = name,
                description = description,
                date = date,
                photoUrl = photoUrl.toString()
            )
            eventViewModel.insertEvents(event)
            Toast.makeText(context, "Has creado un nuevo evento", Toast.LENGTH_SHORT).show()
        }
    }

    //EDICIÓN DE EVENTOS
    private fun setupUiEvents() {
        if (eventViewModel.isEditMode.value!!) {
            mBinding.tvTitle.text = getString(R.string.tv_edit_title)
            mBinding.btnCreateEvent.text = getString(R.string.btn_update_event)
            mBinding.btnDelete.visibility = View.VISIBLE
            event = eventViewModel.event.value!!
            Log.i("Ayutoo2", "${event.id}")
            mBinding.etEventName.setText(event.name)
            mBinding.etEventDescription.setText(event.description)
            mBinding.etDate.setText(event.date)
            Glide.with(mBinding.root).load(event.photoUrl).diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop().into(mBinding.imgPhoto)
        } else {
            Event(0, "", "", "")
        }
    }

    override fun onDestroyView() {
        eventViewModel.isEditMode.value = false
        super.onDestroyView()
    }

    //Eliminar evento
    private fun confirmDelete(event: Event) {
        MaterialAlertDialogBuilder(requireContext()).setTitle(getString(R.string.dialog_delete_title))
            .setPositiveButton(R.string.dialog_delete_confirm) { _, _ ->
                eventViewModel.deleteEvent(event)
            }.setNegativeButton(R.string.dialog_delete_cancel, null).show()
    }
}