package com.example.bohubrihiecommerce.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.bohubrihiecommerce.R
import com.example.bohubrihiecommerce.activities.HomeActivity
import com.example.bohubrihiecommerce.activities.ProductActivity
import com.example.bohubrihiecommerce.models.Category
import com.example.bohubrihiecommerce.utils.Constants

class CategoryListItemAdapter(val context: Context, val categoryList :  ArrayList<Category>) :RecyclerView.Adapter<CategoryListItemAdapter.CategoryListItemViewHolder>() {


    inner class CategoryListItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var nameTextView : TextView
        var parent : ConstraintLayout

        init {
            nameTextView = itemView.findViewById(R.id.categoryName)
            parent = itemView.findViewById(R.id.parent)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryListItemViewHolder {
        return CategoryListItemViewHolder(LayoutInflater.from(context)
            .inflate(R.layout.list_item_category,parent,false))
    }

    override fun onBindViewHolder(holderCategory: CategoryListItemViewHolder, position: Int) {
        holderCategory.nameTextView.text = categoryList.get(position).name
        if(position % 3 == 0){
            holderCategory.parent.setBackgroundColor(context.resources.getColor(R.color.redSemiTransparent))
        }else if(position % 3 ==1){
            holderCategory.parent.setBackgroundColor(context.resources.getColor(R.color.greenSemiTransparent))
        }else{
            holderCategory.parent.setBackgroundColor(context.resources.getColor(R.color.blueSemiTransparent))
        }
        holderCategory.parent.setOnClickListener {
            val intent = Intent(context , ProductActivity::class.java)
            intent.putExtra(Constants.CATEGORY , categoryList.get(position).name)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return categoryList.size
    }
}