package br.com.digao1297.jokerapp.view

import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import br.com.digao1297.jokerapp.R
import br.com.digao1297.jokerapp.model.Category
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item

class CategoryItem( val category: Category) : Item<CategoryItem.CategoryViewHolder>() {

    class CategoryViewHolder(view: View) : GroupieViewHolder(view)

    override fun createViewHolder(itemView: View): CategoryViewHolder = CategoryViewHolder(itemView)

    override fun bind(viewHolder: CategoryViewHolder, position: Int) {
        viewHolder.itemView.findViewById<TextView>(R.id.txt_category).text = category.name
        viewHolder.itemView.findViewById<LinearLayout>(R.id.container_category)
            .setBackgroundColor(category.bgColor.toInt())

    }

    override fun getLayout(): Int = R.layout.item_category


}