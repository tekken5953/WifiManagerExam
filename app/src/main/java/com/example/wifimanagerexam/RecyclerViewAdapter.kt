package com.example.wifimanagerexam

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

/**
 * @author : Lee Jae Young
 * @since : 2023-06-01 오후 2:17
 **/
class RecyclerViewAdapter(private val context: Context, list: ArrayList<DataModel.Wifi>) :
    RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {
    private val mList = list

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        val view: View = inflater.inflate(R.layout.listitem, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = mList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(mList[position])
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val ssid: TextView = itemView.findViewById(R.id.listItemSSID)
        private val address: TextView = itemView.findViewById(R.id.listItemAddress)

        fun bind(dao: DataModel.Wifi) {
            ssid.text = dao.ssid
            address.text = dao.address
        }
    }
}