package app.jimmy.loadersample.Models

/**
 * @author Jimmy
 * Created on 16/4/18.
 */
class ContactData (name:String,phoneNumber:String){
    val name:String
    val phoneNumber:String
    init {
        this.name = name
        this.phoneNumber = phoneNumber
    }
}