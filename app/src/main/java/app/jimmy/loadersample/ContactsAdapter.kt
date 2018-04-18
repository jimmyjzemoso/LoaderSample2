package app.jimmy.loadersample

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import app.jimmy.loadersample.Models.ContactData
import app.jimmy.loadersample.databinding.ContactItemBinding

/**
 * @author Jimmy
 * Created on 16/4/18.
 */
class ContactsAdapter(private var myDataList: List<ContactData>) : RecyclerView.Adapter<ContactsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val lI : LayoutInflater = LayoutInflater.from(parent.context)
        val binding : ContactItemBinding = DataBindingUtil.inflate(lI,R.layout.contact_item,parent,false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return myDataList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(myDataList.get(position))
    }

    class ViewHolder constructor(val binding: ContactItemBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(contactData: ContactData){
            binding.setVariable(BR.contact,contactData)
            binding.executePendingBindings()
        }
    }

    fun updateRecyclerView(contactList: List<ContactData>) {
        myDataList = contactList
        Log.d("Contacts",contactList.toString())
        notifyDataSetChanged()
    }
}