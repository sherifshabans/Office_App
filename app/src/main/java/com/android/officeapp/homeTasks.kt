package com.android.kotlinmvvmtodolist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.android.kotlinmvvmtodolist.ui.task.TaskAdapter
import com.android.kotlinmvvmtodolist.ui.task.TaskViewModel

class homeTasks : Fragment() {
    private val viewModel: TaskViewModel by viewModels()
    private lateinit var mAdapter: TaskAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
         val binding= inflater.inflate(R.layout.fragment_home_tasks, container, false)

        val btn=binding.findViewById<Button>(R.id.tolist1page)
        val btn1=binding.findViewById<Button>(R.id.tolist2page)

        binding.apply {


        btn.setOnClickListener{

            findNavController().navigate(R.id.taskFragment)

        }
        btn1.setOnClickListener{
            findNavController().navigate(R.id.list2)
        }
        }

        return binding.rootView
    }


}