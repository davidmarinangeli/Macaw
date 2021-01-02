package com.david.voicememos.macaw.ui.sortmethodchooser

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.david.voicememos.macaw.databinding.FragmentSortMethodsBinding
import com.david.voicememos.macaw.databinding.SortMethodItemBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class SortMethodListSheetFragment : BottomSheetDialogFragment() {

    private var _binding: FragmentSortMethodsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentSortMethodsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.list.layoutManager = LinearLayoutManager(context)
        binding.list.adapter = SortMethodAdapter(listOf("duration","date","name"))
    }

    private inner class ViewHolder(binding: SortMethodItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        val text: TextView = binding.text
    }

    private inner class SortMethodAdapter(private val data: List<String>) :
        RecyclerView.Adapter<ViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

            return ViewHolder(
                SortMethodItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )

        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.text.text = data[position]
        }

        override fun getItemCount(): Int {
            return data.size
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}