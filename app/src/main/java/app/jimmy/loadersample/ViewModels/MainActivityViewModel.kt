package app.jimmy.loadersample.ViewModels

import android.databinding.ObservableArrayList
import android.databinding.ObservableList
import app.jimmy.loadersample.Interfaces.ContactActions
import app.jimmy.loadersample.Interfaces.ContactListCallbackInterface
import app.jimmy.loadersample.Models.ContactData

/**
 * @author Jimmy
 * Created on 17/4/18.
 */
class MainActivityViewModel(val contactActions: ContactActions) : ContactListCallbackInterface {
    override fun onLoadFailed() {

    }

    var contactList: ObservableList<ContactData> = ObservableArrayList()

    override fun onContactsLoaded(contactDataList: List<ContactData>) {
          contactDataList.forEach{
              contactList.add(it)
          }
    }

    fun getContacts(){
        contactActions.getContacts(this)
    }

}