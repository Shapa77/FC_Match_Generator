package com.shapacreations.generatorfifa22

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.shapacreations.generatorfifa22.databinding.DbItemLayoutBinding

class DbRecyclerViewAdapter (private val contextValue: Context) : RecyclerView.Adapter<DbRecyclerViewAdapter.DbItemHolder>() {
    var adapterClubList = mutableListOf<ClubModel>()


    class DbItemHolder(item:View,contextValue: Context): RecyclerView.ViewHolder(item) {
        val context = contextValue
        val binding = DbItemLayoutBinding.bind(item)

        val listStars = listOf(binding.dbStar1,binding.dbStar2,binding.dbStar3,binding.dbStar4,binding.dbStar5)

        fun bind(club: ClubModel) = with(binding){

            dbClubItemLogo.setImageResource(club.logoId)
            dbClubItemName.text = club.name
            dbClubItemCountry.text = club.country
            dbClubItemDivision.text = club.division

            if(club.sex == getStandardStringByValue(context,context.getString(R.string.Man))) dbClubItemSex.setImageResource(R.drawable.male_icon)
            else dbClubItemSex.setImageResource(R.drawable.female_icon)

            strengthStarSet(listStars,club.strength)


        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DbItemHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.db_item_layout,parent,false)
        return DbItemHolder(view,contextValue)
    }

    override fun onBindViewHolder(holder: DbItemHolder, position: Int) { holder.bind(adapterClubList[position])}
    override fun getItemCount(): Int { return adapterClubList.size}



    @SuppressLint("NotifyDataSetChanged")
    fun refreshData(clubList: List<ClubModel>){
        adapterClubList.clear()
        adapterClubList.addAll(clubList)
        notifyDataSetChanged()
    }

}