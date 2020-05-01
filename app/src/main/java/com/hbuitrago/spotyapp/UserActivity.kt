package com.hbuitrago.spotyapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.hbuitrago.spotyapp.models.UserModel
import com.hbuitrago.spotyapp.repository.UserRepository
import kotlinx.android.synthetic.main.activity_user.*
import java.lang.Exception
import kotlin.concurrent.thread

class UserActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)

        button.setOnClickListener{
            val userModel = UserModel("Hector", "hb", "hb@gmail.com", "1234")
            createThreadToCreateUser(userModel)
        }

    }


    private fun createThreadToCreateUser(userModel: UserModel){
        val thread = Thread(Runnable{
                CreateUserFromRepository(userModel)
        })
        thread.start()
    }

    private fun CreateUserFromRepository(userModel: UserModel) {
        try {
            val repository = UserRepository()
            val result = repository.createUser(userModel)
            showUser(result)
        }catch (e: Exception)
        {
            runOnUiThread{
                Toast.makeText(this, e.message, Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun showUser(userModel: UserModel){
        runOnUiThread{
            Toast.makeText(this, "${userModel.name} ${userModel._id}", Toast.LENGTH_LONG).show()
        }
    }


}
