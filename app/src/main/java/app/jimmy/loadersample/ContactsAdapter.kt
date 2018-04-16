package app.jimmy.loadersample

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

/**
 * @author Jimmy
 * Created on 16/4/18.
 */
class ContactsAdapter(myDataList: ArrayList<ContactData>) : RecyclerView.Adapter<ContactsAdapter.ViewHolder>() {
    var myDataList:ArrayList<ContactData>
    init {
        this.myDataList = myDataList
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.contact_item,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return myDataList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.name.setText(myDataList[position].name)
        holder.phoneNumber.setText(myDataList[position].phoneNumber)

    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var name:TextView
        var phoneNumber:TextView
        init {
            name = itemView.findViewById(R.id.name)
            phoneNumber = itemView.findViewById(R.id.phone_number)
        }
    }
}