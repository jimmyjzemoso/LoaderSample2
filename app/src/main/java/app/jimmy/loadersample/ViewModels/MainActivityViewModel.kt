package app.jimmy.loadersample.ViewModels

import android.databinding.BaseObservable
import android.databinding.ObservableArrayList
import android.databinding.ObservableList
import app.jimmy.loadersample.Interfaces.ContactActions
import app.jimmy.loadersample.Interfaces.ContactListCallbackInterface
import app.jimmy.loadersample.Models.ContactData

/**
 * @author Jimmy
 * Created on 17/4/18.
 */
class MainActivityViewModel(val contactActions: ContactActions) : BaseObservable(), ContactListCallbackInterface {
    override fun onLoadFailed() {

    }

    var contactList: ObservableList<ContactData> = ObservableArrayList<ContactData>()
      /*  @Bindable get() = contactList
        set(value){
        field = value
            notifyPropertyChanged(BR.contactList)
        }*/

    override fun onContactsLoaded(contactDataList: List<ContactData>) {
          contactDataList.forEach{
              contactList.add(it)
          }
    }

    fun getContacts(){
        contactActions.getContacts(this)
    }

    fun addContact(){
        val contactData = ContactData("Bhupathi","12321321321")
        contactList.add(contactData)
    }
}