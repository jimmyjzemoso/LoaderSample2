package app.jimmy.loadersample.Interfaces

import app.jimmy.loadersample.Models.ContactData

/**
 * @author Jimmy
 * Created on 17/4/18.
 */
interface ViewCallback {
    fun updateRecyclerView(contactList : List<ContactData>)
    fun contactsLoadFailed()
}