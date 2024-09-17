package www.mf.mexifood.com

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import www.mf.mexifood.com.databinding.ItemDrinkBinding

class DrinkAdapter(private var drinks: List<Drink>) :
    RecyclerView.Adapter<DrinkAdapter.DrinkViewHolder>() {

    inner class DrinkViewHolder(private val binding: ItemDrinkBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(drink: Drink) {
            binding.drinkName.text = drink.nombre
            binding.drinkPrice.text = "${drink.precio} $"
            binding.drinkDescription.text = drink.descripcion

            // Cargar imagen usando Glide
            Glide.with(binding.root.context)
                .load(drink.imagenUrl)
                .into(binding.drinkImage)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DrinkViewHolder {
        val binding = ItemDrinkBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DrinkViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DrinkViewHolder, position: Int) {
        holder.bind(drinks[position])
    }

    override fun getItemCount(): Int = drinks.size

    fun updateData(newDrinks: List<Drink>) {
        drinks = newDrinks
        notifyDataSetChanged()
    }
}
