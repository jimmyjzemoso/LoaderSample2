package app.jimmy.loadersample.Views

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.Toast
import app.jimmy.loadersample.ContactsAdapter
import app.jimmy.loadersample.Interfaces.ViewCallback
import app.jimmy.loadersample.Models.ContactData
import app.jimmy.loadersample.Models.Source.ContactSystemImpl
import app.jimmy.loadersample.R
import app.jimmy.loadersample.ViewModels.MainActivityViewModel
import kotlinx.android.synthetic.main.activity_main.*



class MainActivity : AppCompatActivity(), ViewCallback{
    private lateinit var viewAdapter: ContactsAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager
    private var myDataset = ArrayList<ContactData>()
    private val MY_PERMISSIONS_REQUEST_READ_CONTACTS = 1920
    private val TAG = "MainActivity"
    lateinit var viewModel : MainActivityViewModel

    //region Lifecycle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewManager = LinearLayoutManager(this)
        viewAdapter = ContactsAdapter(myDataset)
        contact_list.apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }
        viewAdapter.notifyDataSetChanged()
        val contactActions = ContactSystemImpl(applicationContext)
        viewModel = MainActivityViewModel(contactActions, this)
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    arrayOf(Manifest.permission.READ_CONTACTS),
                    MY_PERMISSIONS_REQUEST_READ_CONTACTS)
        }else {
              viewModel.getContacts()
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when(requestCode){
            MY_PERMISSIONS_REQUEST_READ_CONTACTS ->{
                viewModel.getContacts()
            }
        }
    }

    override fun contactsLoadFailed() {
        Toast.makeText(this,"Contacts Load failed",Toast.LENGTH_SHORT).show()
    }

    override fun updateRecyclerView(contactList: List<ContactData>) {
       viewAdapter.updateRecyclerView(contactList)
    }

    //endregion
 /*   //region Other Methods
    private fun contactsFromCursor(cursor: Cursor?) {
        Log.d(TAG,cursor?.getColumnName(0)+" "+cursor?.columnCount+" ")
        if (cursor!!.count > 0) {
            cursor.moveToFirst()

            do {
                val name = cursor.getString(cursor.getColumnIndex(ContactsContract.Data.DISPLAY_NAME))
                val phoneNumber = cursor.getString(cursor.getColumnIndex(ContactsContract.Data.DATA1))
                if(phoneNumber!=null &&  name != null){
                    myDataset.add(ContactData(name, phoneNumber))
                }
            } while (cursor.moveToNext())
            viewAdapter.notifyDataSetChanged()
        }
    }
    //endregion*/


}
