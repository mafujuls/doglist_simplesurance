package me.mafu.dogslist_simplesurance.presentation.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.ScaleAnimation
import android.widget.BaseAdapter
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import me.mafu.dogslist_simplesurance.databinding.PhotoItemBinding

class PhotoAdapter(private var arrayListImage: List<String>) : BaseAdapter() {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        var holder: ViewHolder
        var mConvertView = convertView

        if (mConvertView == null) {

            val photoItemBinding = PhotoItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            mConvertView = photoItemBinding.root

            holder = ViewHolder(photoItemBinding)

            mConvertView.setTag(holder)
        } else {
            holder = mConvertView.tag as ViewHolder
        }

        holder.bind(arrayListImage[position])

        return mConvertView

    }

    override fun getItem(p0: Int): Any {
        return arrayListImage.get(p0)
    }

    override fun getItemId(p0: Int): Long {
        return 0
    }

    override fun getCount(): Int {
        return arrayListImage.size
    }

    inner class ViewHolder(private val photoItemBinding: PhotoItemBinding) {
        fun bind(urls: String) {
            val context: Context = photoItemBinding.breedsPhoto.context

            val requestOptions: RequestOptions = RequestOptions()
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .priority(Priority.HIGH)
                .placeholder(
                    imageLoaderPlaceHolder(
                        context
                    )
                )

            Glide.with(context)
                .load(urls)
                .apply(requestOptions)
                .into(photoItemBinding.breedsPhoto)

            setFadeAnimation(photoItemBinding.root)
        }

        private fun imageLoaderPlaceHolder(context: Context): CircularProgressDrawable {
            val circularProgressDrawable = CircularProgressDrawable(context)
            circularProgressDrawable.strokeWidth = 5f
            circularProgressDrawable.centerRadius = 30f
            circularProgressDrawable.start()
            return circularProgressDrawable
        }
    }

    private fun setFadeAnimation(view: View) {
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