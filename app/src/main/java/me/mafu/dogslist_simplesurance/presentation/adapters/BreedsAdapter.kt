package me.mafu.dogslist_simplesurance.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.ScaleAnimation
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import me.mafu.dogslist_simplesurance.databinding.BreedsItemBinding
import me.mafu.dogslist_simplesurance.domain.models.Breeds

class BreedsAdapter : RecyclerView.Adapter<BreedsAdapter.BreedsViewHolder>() {

    private var breedsItemClickListener: BreedsItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BreedsViewHolder {
        val binding = BreedsItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BreedsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BreedsViewHolder, position: Int) {
        val breeds = differ.currentList[position]
        holder.bind(breeds)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    fun setOnClickListener(breedsItemClickListener: BreedsItemClickListener) {
        this.breedsItemClickListener = breedsItemClickListener
    }

    private val callback = object : DiffUtil.ItemCallback<Breeds>(){
        override fun areItemsTheSame(oldItem: Breeds, newItem: Breeds): Boolean {
            return (oldItem.name == newItem.name) && (oldItem.isFavourite == newItem.isFavourite)
        }

        override fun areContentsTheSame(oldItem: Breeds, newItem: Breeds): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this, callback)


    inner class BreedsViewHolder(private val binding : BreedsItemBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(breeds: Breeds){

            binding.clickListener = breedsItemClickListener
            binding.breeds = breeds


            if (breeds.subBreeds.isEmpty()){
                binding.breedsViewItemSubtitle.text = "${0} Sub breed"
            } else {
                binding.breedsViewItemSubtitle.text = "${breeds.subBreeds.split(",").size} Sub breeds"
            }
            binding.breedsViewItemNumber.text = adapterPosition.plus(1).toString()
            setScaleAnimation(binding.root)
        }
    }


    interface BreedsItemClickListener {
        fun onFavoriteClickListener(breeds: Breeds)
        fun onBreedsClickListener(breeds: Breeds)
    }

    private fun setScaleAnimation(view: View) {
        val anim = ScaleAnimation(
            0.0f,
            1.0f,
            0.0f,
            1.0f,
            Animation.RELATIVE_TO_SELF,
            0.5f,
            Animation.RELATIVE_TO_SELF,
            0.5f
        )
        anim.duration = 400
        view.startAnimation(anim)
    }
}