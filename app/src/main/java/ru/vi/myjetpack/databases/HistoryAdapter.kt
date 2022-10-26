package ru.vi.myjetpack.databases


import android.annotation.SuppressLint
import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView



class HistoryAdapter(
    private val films: History.List,
    private val context: Context,
    val onItemClick:(History, Int) -> Unit): RecyclerView.Adapter<HistoryAdapter.ViewHolder>()
{

    override fun getItemCount() = films.size

  /*  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder
    {
      // Log.d("mylog","onCreateViewHolder viewType=$viewType")

        if(viewType==1) {
            val view =
                LayoutInflater.from(parent.context).inflate(R.layout.more_item, parent, false)
            return ViewHolder(view, 1).apply {
                itemView.setOnClickListener {onItemClick (Film("","","",""),1)

                }}
        }
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.film_item, parent, false)
        return ViewHolder(view, 0).apply {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION)
                    onItemClick(films[position],0)
            }
        }
    }*/

    override fun getItemViewType(position: Int): Int {
   //     Log.d("mylog","getItemViewType position=$position getItemCount=${getItemCount()}")
        if (position==getItemCount()-1 ) return 1
        return 0
    }

    override fun onBindViewHolder(holder: ViewHolder, @SuppressLint("RecyclerView") position: Int)
    {
        //Log.d("mylog","onBindViewHolder position=$position")

    /*    if(holder.itemViewType==0) {
        holder.textViewNameRu?.text = films[position].nameRu

        //Picasso.with(context).load(films[position].posterUrl).into(holder.imageView)

            Picasso.with(context)
                .load(films[position].posterUrl)
                .networkPolicy(NetworkPolicy.OFFLINE)
                .into(holder.imageView, object : Callback {
                    override fun onSuccess() {}
                    override fun onError() {
                        //Try again online if cache failed
                        Picasso.with(context)
                            .load(films[position].posterUrl)
                            .error(R.drawable.image_1)
                            .into(holder.imageView, object : Callback {
                                override fun onSuccess() {}
                                override fun onError() {
                                    Log.v("mylog", "Could not fetch image")
                                }
                            })
                    }
                })


        }*/

    }

   class ViewHolder(view: View,viewType:Int) : RecyclerView.ViewHolder(view)
    {


        /*    var textViewNameRu: TextView? = null
            var imageView: ImageView? = null

            init
            {
                if (viewType == 0)
                {
                textViewNameRu = itemView.findViewById(R.id.nameRu)
                imageView = itemView.findViewById(R.id.posterUrl)
                }

            }*/
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        TODO("Not yet implemented")
    }


}

