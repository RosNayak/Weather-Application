package com.example.weatherapp.ui

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.View
import androidx.fragment.app.DialogFragment
import com.example.weatherapp.R
import kotlinx.android.synthetic.main.change_default_city_dialog.view.*

class ChangeDefaultCityDialog : DialogFragment() {

    private var interfaceImplementer : ChangeDefaultCityDialogInterface? = null
    private lateinit var dialogLayout : View

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val changeDefaultCityDialog = AlertDialog.Builder(context)
        dialogLayout = layoutInflater.inflate(R.layout.change_default_city_dialog, null)
        changeDefaultCityDialog.setView(dialogLayout)
            .setCancelable(false)
        setConfirmButtonListener()
        setCancelButtonListener()
        return changeDefaultCityDialog.create()
    }

    interface ChangeDefaultCityDialogInterface {
        fun onConfirmButtonClicked(newCity : String)
        fun onCancelButtonClicked()
    }

    fun setDialogListener(listener: ChangeDefaultCityDialogInterface){
        interfaceImplementer = listener
    }

    private fun setConfirmButtonListener() {
        dialogLayout.confirmButton.setOnClickListener {
            interfaceImplementer?.onConfirmButtonClicked(dialogLayout.newDefaultCityName.text.toString())
            dismiss()
        }
    }

    private fun setCancelButtonListener() {
        dialogLayout.cancelButton.setOnClickListener {
            interfaceImplementer?.onCancelButtonClicked()
            dismiss()
        }
    }
}