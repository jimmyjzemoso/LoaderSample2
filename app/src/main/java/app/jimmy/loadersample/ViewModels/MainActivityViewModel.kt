package app.jimmy.loadersample.ViewModels

import app.jimmy.loadersample.Interfaces.ContactActions
import app.jimmy.loadersample.Interfaces.ContactListCallbackInterface
import app.jimmy.loadersample.Interfaces.ViewCallback
import app.jimmy.loadersample.Models.ContactData

/**
 * @author Jimmy
 * Created on 17/4/18.
 */
class MainActivityViewModel(val contactActions: ContactActions, val viewCallback: ViewCallback): ContactListCallbackInterface {
    override fun onLoadFailed() {

    }

    override fun onContactsLoaded(contactDataList: List<ContactData>) {
        viewCallback.updateRecyclerView(contactDataList)
    }

    fun getContacts(){
        contactActions.getContacts(this)
    }
}