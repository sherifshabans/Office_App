package com.android.kotlinmvvmtodolist.ui.update

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.android.kotlinmvvmtodolist.R
import com.android.kotlinmvvmtodolist.data.local.TaskEntry
import com.android.kotlinmvvmtodolist.dataNotification.util.Constants
import com.android.kotlinmvvmtodolist.databinding.FragmentUpdateBinding
import com.android.kotlinmvvmtodolist.ui.task.TaskViewModel
import com.example.todolist.data.util.DateToString
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class UpdateFragment : Fragment() {

    private val viewModel: TaskViewModel by viewModels()
    private var _binding: FragmentUpdateBinding? = null
    private val binding get() = _binding!!
    private val args by navArgs<UpdateFragmentArgs>()
    private var taskEntry = TaskEntry(0, Date(Constants.MAX_TIMESTAMP), 0,
        0,"" ,"",""
        ,"","","",0,"","","","",System.currentTimeMillis())
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentUpdateBinding.inflate(inflater, container, false)

       //var str1 =DateToString.convertDateToString(taskEntry.date1)
        //if(str1=="N/A")str1="موعد الجلسة التالية :"
        var str = DateToString.convertDateToString(args.task.date)
        if(str=="N/A") str="موعد الجلسة  :"


//       dateAndTimePickerUpdate1.text = DateToString.convertDateToString(args.task.date)

        binding.apply {

//            dateAndTimePickerUpdate1.text = str

            dateAndTimePickerUpdate1.text = DateToString.convertDateToString(args.task.date)


            val name = args.task.nameOfStation
            updateSpinnerStations.setSelection(name)
            updateSpinnerTypeOFstations.setSelection(args.task.typeOfStation)
            updateEdtNumberofCase.setText(args.task.numberOfCase)
            updateEdtSubject.setText(args.task.subject)
            updateEdtNumberOfCircle.setText(args.task.numberOfCircle)
            updateEdtDescription.setText(args.task.description)
            updateEdtVsname.setText(args.task.vsname)
            updateEdtNote.setText(args.task.note)
            updateSpinner.setSelection(args.task.priority)
           updateEdtname.setText(args.task.name)
            updateEdtnumberofdif.setText(args.task.numberOfdif)
            updateEdtcenter.setText(args.task.center)
            //ClickListeners
            dateAndTimePickerUpdate1.setOnClickListener { showDateTimePicker()}

           ////--------------------------------------

            btnUpdate.setOnClickListener {
           //     dateAndTimePickerUpdate1.text = DateToString.convertDateToString(args.task.date)

                if(TextUtils.isEmpty(updateEdtDescription.text)){
                    Toast.makeText(requireContext(), "أملأ الفراغات!", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener }




                val nameOfStation = updateSpinnerStations.selectedItemPosition
                val numberOfCase = updateEdtNumberofCase.text.toString()
                val subject = updateEdtSubject.text.toString()
                val numberOfCircle = updateEdtNumberOfCircle.text.toString()
                val description = updateEdtDescription.text.toString()
                val vsname = updateEdtVsname.text.toString()
                val  typeOfStation=updateSpinnerTypeOFstations.selectedItemPosition
                val priority = updateSpinner.selectedItemPosition
                val note= updateEdtNote.text.toString()
                val nameof = updateEdtname.text.toString()
                val  center = updateEdtcenter.text.toString()
                val dif = updateEdtnumberofdif.text.toString()

                taskEntry.priority=priority
                taskEntry.nameOfStation=nameOfStation
                taskEntry.numberOfCase=numberOfCase
                taskEntry.subject=subject
                taskEntry.numberOfCircle=numberOfCircle
                taskEntry.note=note
            //    taskEntry.timestamp=args.task.timestamp
                taskEntry.description=description
                taskEntry.vsname=vsname
                taskEntry.id=args.task.id
                taskEntry.typeOfStation=typeOfStation
                taskEntry.timestamp=System.currentTimeMillis()
                taskEntry.center=center
                taskEntry.name=nameof
                taskEntry.numberOfdif=dif


                viewModel.update(taskEntry)
                Toast.makeText(requireContext(), "تم التعديل!", Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.action_updateFragment_to_home2)

            }



        }
       binding.apply {
         //  dateAndTimePickerUpdate1.text = DateToString.convertDateToString(args.task.date)
           dateAndTimePickerUpdate1.setOnClickListener { showDateTimePicker()}

           btnUpdateAdd.setOnClickListener {

          //     dateAndTimePickerUpdate1.setOnClickListener { showDateTimePicker()}

     if(TextUtils.isEmpty(updateEdtDescription.text)){
                    Toast.makeText(requireContext(), "أملأ الفراغات!", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener }
                val nameOfStation = updateSpinnerStations.selectedItemPosition
                val numberOfCase = updateEdtNumberofCase.text.toString()
                val subject = updateEdtSubject.text.toString()
                val numberOfCircle = updateEdtNumberOfCircle.text.toString()
                val description = updateEdtDescription.text.toString()
                val vsname = updateEdtVsname.text.toString()
                val  typeOfStation=updateSpinnerTypeOFstations.selectedItemPosition
                val priority = updateSpinner.selectedItemPosition
                val note= updateEdtNote.text.toString()
               val name = updateEdtname.text.toString()
               val  center = updateEdtcenter.text.toString()
               val dif = updateEdtnumberofdif.text.toString()
        //      dateAndTimePickerUpdate1.text=DateToString.convertDateToString(args.task.date)

            //   taskEntry.date
               taskEntry.priority=priority
                taskEntry.nameOfStation=nameOfStation
                taskEntry.numberOfCase=numberOfCase
                taskEntry.subject=subject
                taskEntry.numberOfCircle=numberOfCircle
                taskEntry.note=note
                taskEntry.timestamp=System.currentTimeMillis()
                taskEntry.description=description
                taskEntry.vsname=vsname
                taskEntry.id=0
                taskEntry.typeOfStation=typeOfStation
                taskEntry.center=center
                taskEntry.name=name
               taskEntry.numberOfdif=dif

                viewModel.insert(taskEntry)
                Toast.makeText(requireContext(), "تم حفظ قضية جديدة!", Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.action_updateFragment_to_home2)
            }

        }
        binding.apply {
            btnDelete.setOnClickListener {
              //    dateAndTimePickerUpdate1.setOnClickListener { showDateTimePicker()}
              //  dateAndTimePickerUpdate1.text = DateToString.convertDateToString(args.task.date)

                val name = args.task.nameOfStation
                updateSpinnerStations.setSelection(name)
                updateSpinnerTypeOFstations.setSelection(args.task.typeOfStation)
                updateEdtNumberofCase.setText(args.task.numberOfCase)
                updateEdtSubject.setText(args.task.subject)
                updateEdtNumberOfCircle.setText(args.task.numberOfCircle)
                updateEdtDescription.setText(args.task.description)
                updateEdtVsname.setText(args.task.vsname)
                updateEdtNote.setText(args.task.note)
                updateSpinner.setSelection(args.task.priority)
                updateEdtname.setText(args.task.name)
                updateEdtnumberofdif.setText(args.task.numberOfdif)
                updateEdtcenter.setText(args.task.center)

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
            binding.dateAndTimePickerUpdate1.text = DateToString.convertDateToString(taskEntry.date)
            timePicker.show(childFragmentManager, "TAG")
        }

        timePicker.addOnPositiveButtonClickListener{
            val cal = Calendar.getInstance()
            cal.time = taskEntry.date
            cal.set(Calendar.HOUR_OF_DAY, timePicker.hour)
            cal.set(Calendar.MINUTE, timePicker.minute)
            cal.set(Calendar.SECOND, 5)
            taskEntry.date = cal.time
          binding.dateAndTimePickerUpdate1.text = DateToString.convertDateToString(taskEntry.date)
        }
        datePicker.show(childFragmentManager,"TAG")
    }

  /*  private fun showDateTimePicker2() {
        val datePicker = MaterialDatePicker.Builder.datePicker().build()
        val timePicker = MaterialTimePicker.Builder().setTimeFormat(TimeFormat.CLOCK_24H).build()
        datePicker.addOnPositiveButtonClickListener {
            val calendar = Calendar.getInstance()
            calendar.timeInMillis = it
            calendar.set(Calendar.HOUR_OF_DAY, 0)
            calendar.set(Calendar.MINUTE, 0)
            calendar.set(Calendar.SECOND, 0)
            taskEntry.date1 = calendar.time
            binding.dateAndTimePickerUpdate2.text = com.android.kotlinmvvmtodolist.dataNotification.util.DateToString.convertDateToString(taskEntry.date1)
            timePicker.show(childFragmentManager, "TAG")
        }

        timePicker.addOnPositiveButtonClickListener{
            val cal = Calendar.getInstance()
            cal.time = taskEntry.date1
            cal.set(Calendar.HOUR_OF_DAY, timePicker.hour)
            cal.set(Calendar.MINUTE, timePicker.minute)
            cal.set(Calendar.SECOND, 5)
            taskEntry.date1 = cal.time
            binding.dateAndTimePickerUpdate2.text =
                com.android.kotlinmvvmtodolist.dataNotification.util.DateToString.convertDateToString(taskEntry.date1)
        }
        datePicker.show(childFragmentManager,"TAG")
    }
*/

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}