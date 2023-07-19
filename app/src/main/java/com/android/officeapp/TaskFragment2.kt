package com.android.kotlinmvvmtodolist

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.android.kotlinmvvmtodolist.databinding.FragmentTask2Binding
import com.android.kotlinmvvmtodolist.ui.task.TaskAdapter
import com.android.kotlinmvvmtodolist.ui.task.TaskClickListener
import com.android.kotlinmvvmtodolist.ui.task.TaskViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@Suppress("DEPRECATION")
@AndroidEntryPoint
class TaskFragment2 : Fragment() {
    private val viewModel: TaskViewModel by viewModels()
    private lateinit var mAdapter: TaskAdapter
    private val args by navArgs<TaskFragment2Args>()
    private var _binding: FragmentTask2Binding? = null
    private val binding get() = _binding!!

    @SuppressLint("UnsafeRepeatOnLifecycleDetector")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentTask2Binding.inflate(inflater, container, false)

        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        mAdapter = TaskAdapter(TaskClickListener { taskEntry ->
            findNavController().navigate(TaskFragment2Directions.actionTaskFragment2ToUpdateFragment(taskEntry))
        })
        val numerOfCase =args.task.numberOfCase

        lifecycleScope.launch{
            repeatOnLifecycle(Lifecycle.State.STARTED){

                viewModel.getTasksByTitle(numerOfCase).observe(viewLifecycleOwner){ tasks->
                    mAdapter.submitList(tasks)
                }
            }
        }


        binding.apply {
            recyclerViewTasks.adapter = mAdapter

            }


        return binding.root
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
