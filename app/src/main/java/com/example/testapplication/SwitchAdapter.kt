package com.example.testapplication

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Switch
import android.widget.TextView
import androidx.appcompat.widget.SwitchCompat

import androidx.recyclerview.widget.RecyclerView
import com.example.testapplication.databinding.SwitchAdapterBinding
import com.example.testapplication.model.SwitchModel

class SwitchAdapter(val context:Context,var list:MutableList<SwitchModel>): RecyclerView.Adapter<SwitchAdapter.SwitchViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SwitchViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)

        val binding = SwitchAdapterBinding.inflate(layoutInflater, parent, false)


        return SwitchViewHolder(binding)
    }


    override fun onBindViewHolder(holder: SwitchViewHolder, position: Int) {
        with(holder) {
            changeState(
                binding.switchTitle,
                binding.switchSubTitle,
                binding.switchButton,
                position
            )
            binding.delete.setOnClickListener {

                removeAt(position)

            }

        }


    }

    private fun changeState(
        title: TextView,
        subTitle: TextView,
        switchButton: SwitchCompat,
        position: Int
    ) {
        switchButton.isChecked=list[position].boolean
        if (list[position].boolean) {
            title.text = context.getString(R.string.on_titile)
            subTitle.text = context.getString(R.string.off_subtitile)
            subTitle.setTextColor(Color.parseColor("#CF2424"))
            title.setTextColor(Color.parseColor("#FF000000"))


        } else {
            title.text = context.getString(R.string.off_titile)
            subTitle.text = context.getString(R.string.on_subtitile)
            subTitle.setTextColor(Color.parseColor("#FF000000"))
            title.setTextColor(Color.parseColor("#CF2424"))
        }
        switchButton.setOnCheckedChangeListener { compoundButton, b ->

            if(compoundButton.isPressed){
                list[position].boolean= b
                notifyItemChanged(position)
            }


        }
    }

    fun removeAt(position: Int) {
        list.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, list.size-position);

    }

    override fun getItemCount(): Int = list.size


    inner class SwitchViewHolder(val binding: SwitchAdapterBinding) :
        RecyclerView.ViewHolder(binding.root)
}