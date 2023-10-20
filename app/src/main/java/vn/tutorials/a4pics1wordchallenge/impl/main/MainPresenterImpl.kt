package vn.tutorials.a4pics1wordchallenge.impl.main

import android.content.Context
import android.view.View
import androidx.navigation.Navigation.findNavController
import vn.tutorials.a4pics1wordchallenge.R
import vn.tutorials.a4pics1wordchallenge.contact.MainScreenContract
import vn.tutorials.a4pics1wordchallenge.model.Pictures
import vn.tutorials.a4pics1wordchallenge.utils.MySharedPreference

class MainPresenterImpl(
    repository: MainScreenContract.Repository,
    view: MainScreenContract.View,
    context: Context
) :
    MainScreenContract.Presenter {
    private val repository: MainScreenContract.Repository
    private val view: MainScreenContract.View
    private val context: Context
    override fun init() {
        val sharedPreference: MySharedPreference? = MySharedPreference.getInstance(context)
        view.currentLevel(sharedPreference!!.level + 1)
        val picture: Pictures = repository.getQuestion(sharedPreference.level)!!
        view.loadImageCurrentLevel(
            picture.picturesOne,
            picture.picturesTwo,
            picture.picturesThree,
            picture.picturesFour
        )
    }

    override fun onClickSetting() {}
    override fun onClickPlay(view: View?) {

        findNavController(view!!).navigate(R.id.action_mainScreen_to_gameScreen)
    }


    override fun onClickInfoButton() {

    }

    init {
        this.repository = repository
        this.view = view
        this.context = context
    }


}
