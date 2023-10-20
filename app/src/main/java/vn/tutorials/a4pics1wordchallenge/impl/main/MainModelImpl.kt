package vn.tutorials.a4pics1wordchallenge.impl.main

import vn.tutorials.a4pics1wordchallenge.contact.MainScreenContract
import vn.tutorials.a4pics1wordchallenge.data.Database
import vn.tutorials.a4pics1wordchallenge.model.Pictures


class MainModelImpl : MainScreenContract.Repository {
    private val pictures: ArrayList<Pictures> = Database.instance!!.allQuestions
    override fun getQuestion(level: Int): Pictures {
        return pictures[level]
    }
}
