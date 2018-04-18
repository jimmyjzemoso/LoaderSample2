package app.jimmy.loadersample

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import app.jimmy.loadersample.Models.ContactData

/**
 * @author Jimmy
 * Created on 16/4/18.
 */
class ContactsAdapter(private var myDataList: List<ContactData>) : RecyclerView.Adapter<ContactsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        //val view = LayoutInflater.from(parent.context).inflate(R.layout.contact_item,parent,false)
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding : ViewDataBinding =
                DataBindingUtil.inflate(layoutInflater, R.layout.contact_item, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return myDataList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(myDataList[position])
    }

    class ViewHolder constructor(val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind(data : ContactData){
            binding.setVariable(BR.contact,data)
            binding.executePendingBindings()
        }
    }

    fun setContactList(contactList: List<ContactData>) {
          this.myDataList = contactList
          notifyDataSetChanged()
    }

  /*  fun updateRecyclerView(contactList: List<ContactData>) {
        myDataList = contactList
        Log.d("Contacts",contactList.toString())
        notifyDataSetChanged()
    }*/
}