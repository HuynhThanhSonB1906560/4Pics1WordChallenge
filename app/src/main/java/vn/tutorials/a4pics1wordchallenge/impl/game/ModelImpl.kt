package vn.tutorials.a4pics1wordchallenge.impl.game

import vn.tutorials.a4pics1wordchallenge.contact.GameScreenContract
import vn.tutorials.a4pics1wordchallenge.data.Database
import vn.tutorials.a4pics1wordchallenge.model.Pictures


class ModelImpl : GameScreenContract.Model {
    private val pictures: ArrayList<Pictures> =
        Database.instance!!.allQuestions
    override operator fun get(level: Int): Pictures {
        return pictures[level]
    }

    override val allLevel: ArrayList<Pictures>
        get() = pictures

}
