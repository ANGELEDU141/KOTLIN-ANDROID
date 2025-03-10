package pe.senati

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import pe.senati.databinding.ItemCommerceBinding

class CommerceAdapter(private var commerces:MutableList<CommerceEntity>, private var listener:OnClickListener):
    RecyclerView.Adapter<CommerceAdapter.ViewHolder>()
{
    //variable perezosa
    private lateinit var mContext:Context

    //clase interna
    inner class ViewHolder(view:View): RecyclerView.ViewHolder(view){
        val binding=ItemCommerceBinding.bind(view)
    }

    //create new views (invoked by the layout manager)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        mContext = parent.context
        val view = LayoutInflater.from(mContext).inflate(R.layout.item_commerce,parent, false)

        return ViewHolder(view)
    }

// Reemplaza el contenido de la vista
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val commerce = commerces.get(position)

        //vista de la tarjeta
        with(holder)
        {
            binding.root.setOnClickListener{
                listener.onClick(commerce)
            }
            binding.tvNameCommerce.text=commerce.name


            binding.chkFavorite.setOnClickListener{
                listener.onFavoriteCommerce(commerce)
            }
            binding.chkFavorite.isChecked=commerce.isFavorite

            binding.root.setOnLongClickListener{
                listener.onDeleteCommerce(commerce)
                true
            }
        }


    }

    override fun getItemCount(): Int {
        return commerces.size
    }

    //others
    fun insertMemory(commerceEntity: CommerceEntity)
    {
        commerces.add(commerceEntity)
        notifyDataSetChanged()
    }

    fun findAllMemory(commercesDB:MutableList<CommerceEntity>)
    {
        this.commerces=commercesDB
        notifyDataSetChanged()
    }

    fun updateMemory(commerceEntity: CommerceEntity)
    {
        val index =commerces.indexOf(commerceEntity)
        if (index != -1)
        {
            commerces.set(index,commerceEntity)
            notifyItemChanged( index)
        }
    }

    fun deleteMemory(commerceEntity: CommerceEntity)
    {
        val index =commerces.indexOf(commerceEntity)
        if (index != -1)
        {
            commerces.removeAt(index)
            notifyItemRemoved(index)
        }
    }

}