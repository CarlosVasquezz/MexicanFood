package www.mf.mexifood.com

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import www.mf.mexifood.com.databinding.ItemProductBinding

class ProductAdapter(private var productos: List<Producto>) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    inner class ProductViewHolder(private val binding: ItemProductBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(producto: Producto) {
            binding.productName.text = producto.nombre
            binding.productPrice.text = "$${producto.precio}"
            binding.productDescription.text = producto.descripcion
            Glide.with(binding.productImage.context)
                .load(producto.imagenUrl)
                .into(binding.productImage)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding = ItemProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(productos[position])
    }

    override fun getItemCount(): Int {
        return productos.size
    }

    fun updateData(newProductos: List<Producto>) {
        productos = newProductos
        notifyDataSetChanged()
    }
}
