package vn.tutorials.a4pics1wordchallenge.impl.game

import android.content.Context
import android.os.Build
import android.os.CountDownTimer
import android.util.Log
import android.view.View
import androidx.annotation.RequiresApi
import androidx.navigation.Navigation.findNavController
import vn.tutorials.a4pics1wordchallenge.contact.GameScreenContract
import vn.tutorials.a4pics1wordchallenge.model.Pictures
import vn.tutorials.a4pics1wordchallenge.utils.MySharedPreference


class PresenterImpl(
    view: GameScreenContract.View,
    model: GameScreenContract.Model,
    context: Context
) :
    GameScreenContract.Presenter {
    private val context: Context
    private val model: GameScreenContract.Model
    private val view: GameScreenContract.View
    private var sharedPreference: MySharedPreference? = null
    private var level = 0
    private var gameData: Pictures? = null
    private lateinit var userAnswer: Array<String?>
    private lateinit var answer: String
    private lateinit var variant: String

    private val countDownTime: Long = 60000
    private var countDownTimer: CountDownTimer? = null



    private var coin = 0
    override fun init(pos: Int) {

        sharedPreference = MySharedPreference.getInstance(context)
        if (sharedPreference?.itemClicked!!) {
            level = pos
            sharedPreference?.itemClicked = false
            if (pos > sharedPreference?.level!!) {
                sharedPreference?.level = pos
            }
        } else {
            level = sharedPreference!!.level
        }

        coin = sharedPreference!!.coinCount
        val player = sharedPreference!!.namePlayer
        val mode = sharedPreference!!.gameMode
        view.levelState(level + 1)
        view.coinState(coin)
        view.nameState(player)
        initCountDownTimer()
        if(mode == "hard"){
            startCountDownTimer()
        }
        gameData = model[level]
        view.loadImages(
            gameData!!.picturesOne,
            gameData!!.picturesTwo,
            gameData!!.picturesThree,
            gameData!!.picturesFour
        )
        answer = gameData?.answerEng!!
        variant = gameData?.variantEng!!

        val size: Int = answer.length
        for (i in 0 until GameScreenContract.MAX_ANSWER) {
            if (i < size) {
                view.showAnswer(i)
                view.clearAnswer(i)
            } else {
                view.hideAnswer(i)
            }
        }
        for (i in 0 until GameScreenContract.VARIANT_COUNT) {
            val text = getVariant(i)
            view.writeVariant(i, text)
            view.showVariant(i)
        }
        userAnswer = arrayOfNulls(answer.length)
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    override fun onClickDeleteButton() {

        if (coin >= 50) {

            val answers: String = answer
            val variants: String = variant
            val ansList = ArrayList<String>()
            val varList = ArrayList<String>()
            for (element in variants) {
                varList.add(element.toString())
            }
            for (element in answers) {
                ansList.add(element.toString())
            }
            for (i in varList.indices) {
                for (j in ansList.indices) {
                    if (varList[i] == ansList[j]) {
                        view.showVariant(i)
                        break
                    } else {
                        view.hideVariant(i)
                    }
                }
            }
            coin -= 50
            view.coinState(coin)

            sharedPreference?.coinCount = coin
        } else {
            view.warningDialog()
            sharedPreference?.coinCount = coin


        }
    }

    override fun onClickMenuButton() {

        view.episodeDialog()
    }

    private fun getVariant(index: Int): String {
        val variants: String = variant
        return variants[index].toString()
    }

    override fun onClickAnswer(index: Int) {

        val text = userAnswer[index]
        if (userAnswer[index] != null) {
            for (i in 0 until GameScreenContract.VARIANT_COUNT) {
                val textVariant = getVariant(i)
                if (text == textVariant && !view.isShownVariant(i)) {
                    view.showVariant(i)
                    view.clearAnswer(index)
                    userAnswer[index] = null
                    return
                }
            }
        }
    }

    override fun onClickVariant(index: Int) {

        val answerIndex = findFirstEmptyIndex()
        if (answerIndex != -1) {
            view.hideVariant(index)
            val text = getVariant(index)
            view.writeAnswer(answerIndex, text)
            userAnswer[answerIndex] = text
            if (findFirstEmptyIndex() == -1 && isWin) {
                coin += 10
                sharedPreference?.coinCount = coin
                stopCountDownTimer()
                if (level == model.allLevel.size - 1) {
                    view.winDialog()

                } else {
                    view.resultDialog(level, coin, answer)
                }
            } else if (findFirstEmptyIndex() == -1 && !isWin) {
                view.animate()
            }
        }
    }

    override fun onClickContinue() {
        level++
        sharedPreference?.level = level
        init(level)
        val mode = sharedPreference!!.gameMode
        if( mode == "hard"){
            if (countDownTimer != null) {
                resetCountDownTimer()
            }
        }
    }
    private fun initCountDownTimer() {
        countDownTimer = object : CountDownTimer(countDownTime, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                val minutes = millisUntilFinished / 1000 / 60
                val seconds = millisUntilFinished / 1000 % 60
                val timeString = String.format("%02d:%02d", minutes, seconds)
                view.updateTimer(timeString)
            }

            override fun onFinish() {
                view.onTimeUp()
            }
        }
    }

    private fun startCountDownTimer() {
        countDownTimer?.start()
    }
    private fun stopCountDownTimer() {
        countDownTimer?.cancel()
    }
    private fun resetCountDownTimer() {
        countDownTimer?.cancel()
        initCountDownTimer()
        if (countDownTimer != null) {
            startCountDownTimer()
        }
    }

    override fun onClickImage(index: Int) {
    }

    override fun onClickFullImage() {
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onYesClick() {
        onClickDeleteButton()
    }


    override fun onClickYesBackButton(){
        countDownTimer?.cancel()

    }

    private fun findFirstEmptyIndex(): Int {
        for (i in userAnswer.indices) {
            if (userAnswer[i] == null) {
                return i
            }
        }
        return -1
    }

    private val isWin: Boolean
        get() {
            var a: String? = ""
            for (s in userAnswer) {
                a += s
            }
            return a == answer
        }

    init {
        this.view = view
        this.model = model
        this.context = context
    }
}
