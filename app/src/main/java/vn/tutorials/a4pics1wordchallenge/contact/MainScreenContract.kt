package vn.tutorials.a4pics1wordchallenge.contact

import vn.tutorials.a4pics1wordchallenge.model.Pictures


class MainScreenContract {
    interface Presenter {
        fun init()
        fun onClickSetting()
        fun onClickPlay(view: android.view.View?)
        fun onClickInfoButton()
    }

    interface View {
        fun coinCount()
        fun loadImageCurrentLevel(resIdOne: Int, resIdTwo: Int, resIdThree: Int, resIdFour: Int)
        fun currentLevel(level: Int)
        fun modeDialog()
        fun infoDialog()
        fun createDialog()
        fun settingDialog()
    }

    interface Repository {
        fun getQuestion(level: Int): Pictures?
    }
}
