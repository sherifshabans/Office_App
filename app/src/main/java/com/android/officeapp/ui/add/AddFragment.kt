package com.android.kotlinmvvmtodolist.ui.add

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.android.kotlinmvvmtodolist.R
import com.android.kotlinmvvmtodolist.data.local.TaskEntry
import com.android.kotlinmvvmtodolist.dataNotification.util.Constants
import com.android.kotlinmvvmtodolist.dataNotification.util.DateToString
import com.android.kotlinmvvmtodolist.databinding.FragmentAddBinding
import com.android.kotlinmvvmtodolist.ui.task.TaskViewModel
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class AddFragment : Fragment() {

    private val viewModel: TaskViewModel by viewModels()
    private var taskEntry = TaskEntry(0, Date(Constants.MAX_TIMESTAMP), 0,
        0,"" ,"",""
        ,"","","",0,"","","","",System.currentTimeMillis())

    private var _binding: FragmentAddBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddBinding.inflate(inflater, container, false)
        val myAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, resources.getStringArray(R.array.priorities))
        val myAdapterStaion = ArrayAdapter(requireContext(),android.R.layout.simple_spinner_dropdown_item,   resources.getStringArray(R.array.Stations))
        val myAdapterTypeOfStaion = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, resources.getStringArray(R.array.typeOfStations))
        var str = DateToString.convertDateToString(taskEntry.date)
        if(str=="N/A")str="موعد الجلسة "
       // var str1 = DateToString.convertDateToString(taskEntry.date1)
        //if(str1=="N/A")str1="موعد الجلسة القادمة"
        binding.apply {
        btnDeleteAdd.setOnClickListener {
            edtNote.text.clear()
            spinnerTypeOfStaion.selectedItemPosition
            spinnerStaion.selectedItemPosition
            //val priority = spinner.selectedItemPosition 
            edtNumberOFCase.text.clear()
            edtSubject.text.clear()
            edtNumberOfCircle.text.clear()
            edtDescription.text.clear()
            edtVsname.text.clear()
           edtCenter.text.clear()
            edtNumberOfdif.text.clear()
            edtName.text.clear()
        }
        }
        binding.apply {
            spinner.adapter = myAdapter
            spinnerStaion.adapter=myAdapterStaion
            spinnerTypeOfStaion.adapter=myAdapterTypeOfStaion
            dateAndTimePickerAdd1.text = str


            //ClickListeners
            dateAndTimePickerAdd1.setOnClickListener { showDateTimePicker()}

        //------------------------

            btnAdd.setOnClickListener {

                if(TextUtils.isEmpty((edtSubject.text))){
                    Toast.makeText(requireContext(), "أكمل الفراغ!", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
                val priority = spinner.selectedItemPosition
                val note =edtNote
                val typeOfStaion= spinnerTypeOfStaion.selectedItemPosition
                val nameOfStation =  spinnerStaion.selectedItemPosition
             //   val priority = spinner.selectedItemPosition
                val numberofcase =edtNumberOFCase.text.toString()
                val subject =edtSubject.text.toString()
                val numberOfCircle =edtNumberOfCircle.text.toString()
                val description =edtDescription.text.toString()
                val vsname =edtVsname.text.toString()
                val name =edtName.text.toString()
                val number= edtNumberOfdif.text.toString()
                val center=edtCenter.text.toString()
               taskEntry.priority=priority
                taskEntry.nameOfStation=nameOfStation
                taskEntry.numberOfCase=numberofcase
                taskEntry.subject=subject
                taskEntry.numberOfCircle=numberOfCircle
                taskEntry.note=note.text.toString()
                taskEntry.timestamp=System.currentTimeMillis()
                taskEntry.description=description
                taskEntry.vsname=vsname
                taskEntry.id=0
                taskEntry.typeOfStation=typeOfStaion
                taskEntry.name=name
                taskEntry.center=center
                taskEntry.numberOfdif=number

                viewModel.insert(taskEntry)
                Toast.makeText(requireContext(), "تمت إضافة القضية بنجاح!", Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.action_addFragment_to_home2)
         
            }
        }
        return binding.root
    }
    private fun showDateTimePicker() {
        val datePicker = MaterialDatePicker.Builder.datePicker().build()
        val timePicker = MaterialTimePicker.Builder().setTimeFormat(TimeFormat.CLOCK_24H).build()
        datePicker.addOnPositiveButtonClickListener {
            val calendar = Calendar.getInstance()
            calendar.timeInMillis = it
            calendar.set(Calendar.HOUR_OF_DAY, 0)
            calendar.set(Calendar.MINUTE, 0)
            calendar.set(Calendar.SECOND, 0)
            taskEntry.date = calendar.time
            binding.dateAndTimePickerAdd1.text = DateToString.convertDateToString(taskEntry.date)
            timePicker.show(childFragmentManager, "TAG")
        }

   /*     timePicker.addOnPositiveButtonClickListener{
            val cal = Calendar.getInstance()
            cal.time = taskEntry.date
            cal.set(Calendar.HOUR_OF_DAY, timePicker.hour)
            cal.set(Calendar.MINUTE, timePicker.minute)
            cal.set(Calendar.SECOND, 5)
            taskEntry.date = cal.time
            binding.dateAndTimePickerAdd1.text = DateToString.convertDateToString(taskEntry.date)
        }*/
        datePicker.show(childFragmentManager,"TAG")
    }

  /*private fun showDateTimePicker2() {
        val datePicker = MaterialDatePicker.Builder.datePicker().build()
        val timePicker = MaterialTimePicker.Builder().setTimeFormat(TimeFormat.CLOCK_24H).build()
        datePicker.addOnPositiveButtonClickListener {
            val calendar = Calendar.getInstance()
            calendar.timeInMillis = it
            calendar.set(Calendar.HOUR_OF_DAY, 0)
            calendar.set(Calendar.MINUTE, 0)
            calendar.set(Calendar.SECOND, 0)
            taskEntry.date1 = calendar.time
            binding.dateAndTimePickerAdd2.text = DateToString.convertDateToString(taskEntry.date1)
            timePicker.show(childFragmentManager, "TAG")
        }

        timePicker.addOnPositiveButtonClickListener{
            val cal = Calendar.getInstance()
            cal.time = taskEntry.date1
            cal.set(Calendar.HOUR_OF_DAY, timePicker.hour)
            cal.set(Calendar.MINUTE, timePicker.minute)
            cal.set(Calendar.SECOND, 5)
            taskEntry.date1 = cal.time
            binding.dateAndTimePickerAdd2.text = DateToString.convertDateToString(taskEntry.date1)
        }
        datePicker.show(childFragmentManager,"TAG")
    }
*/


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}