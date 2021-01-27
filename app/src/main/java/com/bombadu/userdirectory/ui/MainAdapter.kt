package com.bombadu.userdirectory.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bombadu.userdirectory.R
import com.bombadu.userdirectory.data.UserData
import com.bombadu.userdirectory.databinding.ActivityMainBinding

class MainAdapter(val mItemClickListener: ItemClickListener, private val interaction: Interaction? = null
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    interface  ItemClickListener{
        fun onItemClick(position: Int)
    }

    companion object {
        //Constants, if any go here

    }

    private val diffCallBack = object : DiffUtil.ItemCallback<UserData>() {

        override fun areItemsTheSame(oldItem: UserData, newItem: UserData): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: UserData, newItem: UserData): Boolean {
            return oldItem == newItem
        }

    }
    private val differ = AsyncListDiffer(this, diffCallBack)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return MainHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_layout,
                parent,
                false
            ),
            interaction
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {


        when (holder) {
            is MainHolder -> {
                holder.bind(differ.currentList[position])

            }
        }


    }

    fun getItemAt(position: Int): UserData {
        return differ.currentList[position]
        //Use this for getting selected item data from Activity/ Fragment
    }


    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    fun submitList(list: List<UserData>) {
        differ.submitList(list)
    }

    inner class MainHolder(
        itemView: View,
        private val interaction: Interaction?
    ) : RecyclerView.ViewHolder(itemView) {

        init {
            itemView.setOnClickListener {
                mItemClickListener.onItemClick(adapterPosition)

            }
        }

        fun bind(item: UserData) = with(itemView) {

            val nameTextView = itemView.findViewById<TextView>(R.id.name_text_view)
            val locationTextView = itemView.findViewById<TextView>(R.id.location_text_view)
            val ageTextView = itemView.findViewById<TextView>(R.id.age_text_view)

            itemView.setOnClickListener {

                interaction?.onItemSelected(adapterPosition, item)
                mItemClickListener.onItemClick(adapterPosition)


            }


            nameTextView.text = "${item.firstName} ${item.lastName}"
            locationTextView.text = item.location
            ageTextView.text = item.age



        }


    }

    interface Interaction {
        fun onItemSelected(position: Int, item: UserData)
    }


}



