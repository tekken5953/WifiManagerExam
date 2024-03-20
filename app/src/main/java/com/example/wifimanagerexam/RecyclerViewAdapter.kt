package com.example.wifimanagerexam

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView

/**
 * @author : Lee Jae Young
 * @since : 2023-06-01 오후 2:17
 **/
class RecyclerViewAdapter(private val context: Context, list: ArrayList<DataModel.Wifi>) :
    RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {
    private val mList = list

    var isCapability = false

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
//        private val address: TextView = itemView.findViewById(R.id.listItemAddress)
        private val level: ImageView = itemView.findViewById(R.id.listItemLevel)
        private val cap: ImageView = itemView.findViewById(R.id.listItemWifiLock)
        private val freq: TextView = itemView.findViewById(R.id.listItemFreq)

        fun bind(dao: DataModel.Wifi) {
            ssid.text = dao.ssid
//            address.text = dao.address
            freq.text = parseFrequency(dao.frequency)

            dao.level?.let {
                level.setImageDrawable(parseLevel(it))
            } ?: run { level.visibility = View.GONE }

            dao.capabilities?.let {
                cap.visibility = if (isCapability(it)) View.VISIBLE else View.GONE
                isCapability = isCapability(it)
            } ?: run { cap.visibility = View.GONE }
        }
    }

    // 비밀번호 유무
    fun isCapability(capabilities: String): Boolean {
        return capabilities.contains("WPA") || capabilities.contains("WPA2")
                || capabilities.contains("WEP")
    }

    private fun parseLevel(level: Int): Drawable? {
        return ResourcesCompat.getDrawable(
            context.resources,
            when {
                level >= -30 -> { R.drawable.level_wifi_4 }
                level in -30 downTo -60 -> { R.drawable.level_wifi_4 }
                level in -60 downTo -70 -> { R.drawable.level_wifi_3 }
                else -> { R.drawable.level_wifi_1 }
            }, null
        )
    }

    private fun parseFrequency(frequency: Int): String {
        return if (frequency in 2400..2484) {
            "2.4G"
        } else if (frequency >= 5000) {
            "5G"
        } else {
            ""
        }
    }
}