
package com.android.kotlinmvvmtodolist

import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.*
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.android.kotlinmvvmtodolist.dataNotification.util.Constants
import com.android.kotlinmvvmtodolist.databinding.FragmentList2Binding
import com.android.kotlinmvvmtodolist.presentation.MainActiv
import com.android.kotlinmvvmtodolist.ui.task.TaskAdapter
import com.android.kotlinmvvmtodolist.ui.task.TaskClickListener
import com.android.kotlinmvvmtodolist.ui.task.TaskViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.util.*

@Suppress("DEPRECATION")
@AndroidEntryPoint
class List2 : Fragment() {

    private val viewModel: TaskViewModel by viewModels()
    private lateinit var mAdapter: TaskAdapter
private  var fromvalue :Date =Date(Constants.MAX_TIMESTAMP)
private  var tovalue :Date =Date(Constants.MAX_TIMESTAMP)
    private var _binding: FragmentList2Binding? = null
    private val binding get() = _binding!!

    @SuppressLint("UnsafeRepeatOnLifecycleDetector", "SuspiciousIndentation")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentList2Binding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        mAdapter = TaskAdapter(TaskClickListener { taskEntry ->
            findNavController().navigate(List2Directions.actionList2ToTaskFragment2(taskEntry))
        })
//    val btn = requireActivity().findViewById<Button>(R.id.btn_go2)

        lifecycleScope.launch{
            repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.getAllTasks2.collect{ tasks ->
                    mAdapter.submitList(tasks)
                }

            }
        }

        binding.apply {

            recyclerViewList2.adapter = mAdapter

                      //fromList2.setOnClickListener{ fromListDate()}
                      // toList2.setOnClickListener{ toListDate()}
       /*     btnGo2.setOnClickListener {
                Toast.makeText(requireContext(),fromvalue.toString(),Toast.LENGTH_LONG).show()
                Toast.makeText(requireContext(),tovalue.toString(),Toast.LENGTH_LONG).show()

            }
*/
            bottomNavList2.setOnItemSelectedListener {
                when(it.itemId) {


                    R.id.addmenu -> {
                        findNavController().navigate(R.id.addFragment)
                        true
                    }
                    R.id.alltask -> {
                        findNavController().navigate(R.id.home2)
                        true
                    }
                    R.id.notifMenu ->{
                        val intent = Intent(this@List2.requireContext(), MainActiv::class.java)
                        startActivity(intent)
                        true
                    }
                    R.id.idList ->{

                        true
                    }
                    R.id.idList2 -> {
                        findNavController().navigate(R.id.taskFragment)
                        true
                    }
                    else -> {
                        true
                    }
                }

            }



        }



        ItemTouchHelper(object  : ItemTouchHelper.SimpleCallback(0,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT){
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val taskEntry = mAdapter.currentList[position]
                viewModel.delete(taskEntry)

                Snackbar.make(binding.root, "تم الحذف!", Snackbar.LENGTH_LONG).apply {
                    setAction("تراجع"){
                        viewModel.insert(taskEntry)
                    }
                    show()
                }
            }
        }).attachToRecyclerView(binding.recyclerViewList2)

   //     setHasOptionsMenu(true)

   //    hideKeyboard(requireActivity())

        return binding.root
    }
   /* private fun fromListDate() {
        val datePicker = MaterialDatePicker.Builder.datePicker().build()
        val timePicker = MaterialTimePicker.Builder().setTimeFormat(TimeFormat.CLOCK_24H).build()
        var fromlist =Date(Constants.MAX_TIMESTAMP)
        datePicker.addOnPositiveButtonClickListener {
            val calendar = Calendar.getInstance()
            calendar.timeInMillis = it
            calendar.set(Calendar.HOUR_OF_DAY, 0)
            calendar.set(Calendar.MINUTE, 0)
            calendar.set(Calendar.SECOND, 0)
            fromlist = calendar.time
            fromvalue=fromlist
            binding.fromList2.text = DateToString.convertDateToString(fromlist)
            timePicker.show(childFragmentManager, "TAG")
        }

        timePicker.addOnPositiveButtonClickListener{
            val cal = Calendar.getInstance()
            cal.time = fromlist
            cal.set(Calendar.HOUR_OF_DAY, timePicker.hour)
            cal.set(Calendar.MINUTE, timePicker.minute)
            cal.set(Calendar.SECOND, 5)
            fromlist = cal.time
            binding.fromList2.text = DateToString.convertDateToString(fromlist)
        }
        datePicker.show(childFragmentManager,"TAG")
    }
    private fun toListDate() {
        val datePicker = MaterialDatePicker.Builder.datePicker().build()
        val timePicker = MaterialTimePicker.Builder().setTimeFormat(TimeFormat.CLOCK_24H).build()
        var toList =Date(Constants.MAX_TIMESTAMP)
        datePicker.addOnPositiveButtonClickListener {
            val calendar = Calendar.getInstance()
            calendar.timeInMillis = it
            calendar.set(Calendar.HOUR_OF_DAY, 0)
            calendar.set(Calendar.MINUTE, 0)
            calendar.set(Calendar.SECOND, 0)
            toList = calendar.time
            tovalue=toList
            binding.toList2.text = DateToString.convertDateToString(toList)
            timePicker.show(childFragmentManager, "TAG")
        }
        timePicker.addOnPositiveButtonClickListener{
            val cal = Calendar.getInstance()
            cal.time = toList
            cal.set(Calendar.HOUR_OF_DAY, timePicker.hour)
            cal.set(Calendar.MINUTE, timePicker.minute)
            cal.set(Calendar.SECOND, 5)
            toList = cal.time
            binding.toList2.text = DateToString.convertDateToString(toList)
        }
        datePicker.show(childFragmentManager,"TAG")
    }
*/

    private fun hideKeyboard(activity: Activity) {
        val inputMethodManager = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        val currentFocusedView = activity.currentFocus
        currentFocusedView.let {
            inputMethodManager.hideSoftInputFromWindow(
                currentFocusedView?.windowToken, InputMethodManager.HIDE_NOT_ALWAYS
            )
        }
    }
/*
    @Deprecated("Deprecated in Java")
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.task_menu, menu)
        val searchItem = menu.findItem(R.id.action_search)
        val searchView = searchItem.actionView as SearchView
        searchView.setOnQueryTextListener(object  : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }


            override fun onQueryTextChange(newText: String?): Boolean {
                if(newText != null){
                    runQuery(newText)
                }
                return true
            }
        })
    }

    fun runQuery(query: String){
        val searchQuery = "%$query%"
        viewModel.searchDatabaseList(searchQuery).observe(viewLifecycleOwner) { tasks ->
            mAdapter.submitList(tasks)
        }
    }
*/
    @Deprecated("Deprecated in Java")
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.action_delete_all -> deleteAllItem()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteAllItem() {
        AlertDialog.Builder(requireContext())
            .setTitle("حذف الكل")
            .setMessage("هل أنت متأكد من ذلك")
            .setPositiveButton("نعم"){dialog, _ ->
                viewModel.deleteAll()
                dialog.dismiss()
            }.setNegativeButton("لا"){dialog, _ ->
                dialog.dismiss()
            }.create().show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}