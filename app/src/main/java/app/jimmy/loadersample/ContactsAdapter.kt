package app.jimmy.loadersample

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import app.jimmy.loadersample.Models.ContactData

/**
 * @author Jimmy
 * Created on 16/4/18.
 */
class ContactsAdapter(private var myDataList: List<ContactData>) : RecyclerView.Adapter<ContactsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.contact_item,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return myDataList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.name.text = myDataList[position].name
        holder.phoneNumber.text = myDataList[position].phoneNumber
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var name:TextView = itemView.findViewById(R.id.name)
        var phoneNumber:TextView = itemView.findViewById(R.id.phone_number)
    }

    fun updateRecyclerView(contactList: List<ContactData>) {
        myDataList = contactList
        Log.d("Contacts",contactList.toString())
        notifyDataSetChanged()
    }
}