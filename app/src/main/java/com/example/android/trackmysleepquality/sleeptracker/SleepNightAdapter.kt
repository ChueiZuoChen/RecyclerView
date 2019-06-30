/*
 * Copyright 2018, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.trackmysleepquality.sleeptracker

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.android.trackmysleepquality.R
import com.example.android.trackmysleepquality.convertDurationToFormatted
import com.example.android.trackmysleepquality.database.SleepNight
import kotlinx.android.synthetic.main.list_item_sleep_night.view.*


class SleepNightAdapter : RecyclerView.Adapter<SleepNightViewHolder>() {

    var data = listOf<SleepNight>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SleepNightViewHolder {
        return SleepNightViewHolder(LayoutInflater.from(parent.context)
                .inflate(R.layout.list_item_sleep_night, parent, false))
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: SleepNightViewHolder, position: Int) {
        val item = data[position]
        holder.bind(item)

    }
}

class SleepNightViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val sleepLength = itemView.sleep_length
    val qualityImage = itemView.quality_image
    val qualityString = itemView.quality_string

    fun bind(item:SleepNight) {
        sleepLength.text = convertDurationToFormatted(item.startTimeMilli,item.endTimeMilli,itemView.context.resources)
        qualityString.text = item.sleepQuality.toString()
        qualityImage.setImageResource(when(item.sleepQuality){
            0->R.drawable.ic_sleep_0
            1->R.drawable.ic_sleep_1
            2->R.drawable.ic_sleep_2
            3->R.drawable.ic_sleep_3
            4->R.drawable.ic_sleep_4
            5->R.drawable.ic_sleep_5
            else -> R.drawable.ic_sleep_active
        })
    }
}


