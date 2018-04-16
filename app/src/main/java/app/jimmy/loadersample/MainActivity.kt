package app.jimmy.loadersample

import android.Manifest
import android.app.LoaderManager
import android.content.CursorLoader
import android.content.Loader
import android.content.pm.PackageManager
import android.database.Cursor
import android.os.Bundle
import android.provider.ContactsContract
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*



class MainActivity : AppCompatActivity() ,LoaderManager.LoaderCallbacks<Cursor> {
    lateinit var viewAdapter: RecyclerView.Adapter<*>
    lateinit var viewManager: RecyclerView.LayoutManager
    var myDataset = ArrayList<ContactData>()
    val MY_PERMISSIONS_REQUEST_READ_CONTACTS = 1920
    val TAG = "MainActivity"
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
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    arrayOf(Manifest.permission.READ_CONTACTS),
                    MY_PERMISSIONS_REQUEST_READ_CONTACTS)
        }else {
            getLoaderManager().initLoader(0, null, this);
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when(requestCode){
            MY_PERMISSIONS_REQUEST_READ_CONTACTS ->{
                getLoaderManager().initLoader(0, null, this);
            }
        }
    }
    //endregion

    //region Implemented Methods
    override fun onCreateLoader(p0: Int, p1: Bundle?): CursorLoader {
        val contactsUri = ContactsContract.Data.CONTENT_URI // The content URI of the phone contacts

        val PROJECTION = arrayOf(ContactsContract.Data.DISPLAY_NAME, ContactsContract.Data.DATA1)


        return CursorLoader(this,
                contactsUri,
                PROJECTION,
                null,
                null,
                null);
    }

    override fun onLoadFinished(p0: Loader<Cursor>, p1: Cursor) {
        contactsFromCursor(p1)
    }

    override fun onLoaderReset(p0: Loader<Cursor>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    //endregion

    //region Other Methods
    private fun contactsFromCursor(cursor: Cursor) {
        Log.d(TAG,cursor.getColumnName(0)+" "+cursor.columnCount+" ")
        if (cursor.count > 0) {
            cursor.moveToFirst()

            do {
                var name = cursor.getString(cursor.getColumnIndex(ContactsContract.Data.DISPLAY_NAME))
                var phoneNumber = cursor.getString(cursor.getColumnIndex(ContactsContract.Data.DATA1));
                if(phoneNumber==null){
                    phoneNumber = "empty"
                }
                myDataset.add(ContactData(name,phoneNumber))
            } while (cursor.moveToNext())
            viewAdapter.notifyDataSetChanged()
        }
    }
    //endregion
}
