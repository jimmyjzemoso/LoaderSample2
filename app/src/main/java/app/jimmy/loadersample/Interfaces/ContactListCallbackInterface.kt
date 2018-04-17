package app.jimmy.loadersample.Interfaces

import app.jimmy.loadersample.Models.ContactData

/**
 * @author Jimmy
 * Created on 17/4/18.
 */
interface ContactListCallbackInterface {
    fun onContactsLoaded(contactDataList : List<ContactData>)
    fun onLoadFailed()
}