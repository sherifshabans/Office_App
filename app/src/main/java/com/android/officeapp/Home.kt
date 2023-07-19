package com.android.kotlinmvvmtodolist

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.*
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.android.kotlinmvvmtodolist.databinding.FragmentHomeBinding
import com.android.kotlinmvvmtodolist.presentation.MainActiv
import com.android.kotlinmvvmtodolist.ui.task.TaskAdapter
import com.android.kotlinmvvmtodolist.ui.task.TaskClickListener
import com.android.kotlinmvvmtodolist.ui.task.TaskViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@Suppress("DEPRECATION")
@AndroidEntryPoint
class Home : Fragment() {
    private val viewModel: TaskViewModel by viewModels()
    private lateinit var mAdapter: TaskAdapter
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    @SuppressLint("UnsafeRepeatOnLifecycleDetector")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        mAdapter = TaskAdapter(TaskClickListener { taskEntry ->
            findNavController().navigate(HomeDirections.actionHome2ToTaskFragment2(taskEntry))
        })

        lifecycleScope.launch{
            repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.getAllTasks.collect{ tasks ->
                    mAdapter.submitList(tasks)
                }
            }
        }

        binding.apply {
            recyclerViewHome.adapter = mAdapter
            bottomNavHome.setOnItemSelectedListener {

                when(it.itemId) {


                    R.id.addmenu -> {
                        findNavController().navigate(R.id.addFragment)
                        true
                    }
                    R.id.alltask -> {
                        true
                    }
                    R.id.notifMenu ->{
                        val intent = Intent(this@Home.requireContext(), MainActiv::class.java)
                        startActivity(intent)
                        true
                    }
                    R.id.idList ->{
                        findNavController().navigate(HomeDirections.actionHome2ToList2())
                        true
                    }
                    R.id.idList2 -> {
                        findNavController().navigate(HomeDirections.actionHome2ToTaskFragment())
                        true
                    } else -> {
                        true
                    }
                }

            }

        }
/*
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
        }).attachToRecyclerView(binding.recyclerViewHome)
*/
        setHasOptionsMenu(true)

        hideKeyboard(requireActivity())

        return binding.root
    }

    private fun hideKeyboard(activity: Activity) {
        val inputMethodManager = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        val currentFocusedView = activity.currentFocus
        currentFocusedView.let {
            inputMethodManager.hideSoftInputFromWindow(
                currentFocusedView?.windowToken, InputMethodManager.HIDE_NOT_ALWAYS
            )
        }
    }

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
        viewModel.searchDatabase(searchQuery).observe(viewLifecycleOwner) { tasks ->
            mAdapter.submitList(tasks)
        }
    }
    @Deprecated("Deprecated in Java")
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
       /*     R.id.action_priority -> {
                lifecycleScope.launch{
                    repeatOnLifecycle(Lifecycle.State.STARTED){
                        viewModel.getAllPriorityTasks.collectLatest { tasks ->
                            mAdapter.submitList(tasks)
                        }
                    }
                }
            }
         */
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
