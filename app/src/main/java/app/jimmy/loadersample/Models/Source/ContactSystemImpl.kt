package app.jimmy.loadersample.Models.Source

import android.content.Context
import android.os.AsyncTask
import android.provider.ContactsContract
import android.util.Log
import app.jimmy.loadersample.Interfaces.ContactActions
import app.jimmy.loadersample.Interfaces.ContactListCallbackInterface
import app.jimmy.loadersample.Models.ContactData

/**
 * @author Jimmy
 * Created on 17/4/18.
 */
class ContactSystemImpl(private val mContext: Context) :ContactActions {

    override fun getContacts(callbackInterface: ContactListCallbackInterface) {
        val contactAsyncTask = object: AsyncTask<Context, Void, List<ContactData>> () {
            override fun doInBackground(vararg p0: Context?): List<ContactData> {
                var contactList = mutableListOf<ContactData>()
                val context = p0[0]
                val contentResolver = context!!.contentResolver
                val cursor = contentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null)
                Log.d("contacts", cursor.count.toString())
                if(cursor.count>0){
                    cursor.moveToFirst()
                    do {
                        var phone = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))
                        var name = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME))
                        contactList.add(ContactData(name,phone))
                    }while (cursor.moveToNext())
                    cursor.close()
                }
                return contactList
            }

            override fun onPostExecute(result: List<ContactData>?) {
                Log.d("contacts",result.toString())
                if (result != null) {
                    callbackInterface.onContactsLoaded(result)
                }
            }
        }
        contactAsyncTask.execute(mContext)
    }
}