package vn.tutorials.a4pics1wordchallenge.screen

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.media.MediaPlayer
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import kotlinx.android.synthetic.main.create_player_dialog.view.btn_start
import vn.tutorials.a4pics1wordchallenge.R
import vn.tutorials.a4pics1wordchallenge.contact.MainScreenContract
import vn.tutorials.a4pics1wordchallenge.impl.main.MainModelImpl
import vn.tutorials.a4pics1wordchallenge.impl.main.MainPresenterImpl
import vn.tutorials.a4pics1wordchallenge.utils.MySharedPreference
import kotlinx.android.synthetic.main.info_dialog.view.*
import kotlinx.android.synthetic.main.main_screen.*
import kotlinx.android.synthetic.main.mode_dialog.view.easyButton
import kotlinx.android.synthetic.main.mode_dialog.view.hardButton
import java.util.*

class MainScreen : Fragment(R.layout.main_screen), MainScreenContract.View {
    private lateinit var presenter: MainScreenContract.Presenter
    private var sharedPreference: MySharedPreference? = null
    private lateinit var buttonsSound: MediaPlayer
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedPreference = MySharedPreference.getInstance(requireContext())
        presenter = MainPresenterImpl(MainModelImpl(), this, requireContext())
        (presenter as MainPresenterImpl).init()

        coinCount()
        play_info.setOnClickListener {
            infoDialog()
        }
        play.setOnClickListener {
            createDialog()
        }
    }


    override fun coinCount() {
        coinsCount.text = sharedPreference?.coinCount.toString()
    }

    override fun loadImageCurrentLevel(
        resIdOne: Int,
        resIdTwo: Int,
        resIdThree: Int,
        resIdFour: Int
    ) {
        image_one.setImageResource(resIdOne)
        image_two.setImageResource(resIdTwo)
        image_three.setImageResource(resIdThree)
        image_four.setImageResource(resIdFour)
    }

    override fun currentLevel(levels: Int) {
        level.text = levels.toString()
    }

    override fun settingDialog() {

    }

    override fun createDialog() {
        val playerName = MySharedPreference.getInstance(requireContext())?.namePlayer
        if (playerName != null && playerName.isNotEmpty()) {
            modeDialog()
        } else {
            val dialog = AlertDialog.Builder(requireContext()).create()
            val dialogBinding =
                LayoutInflater.from(requireContext()).inflate(R.layout.create_player_dialog, null, false)

            dialog.setCancelable(false)
            dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

            val btnStart: Button = dialogBinding.findViewById(R.id.btn_start)
            val etName: EditText = dialogBinding.findViewById(R.id.et_name)

            btnStart.setOnClickListener {
                val playerNameInput = etName.text.toString().trim()
                if (playerNameInput.isNotEmpty()) {
                    MySharedPreference.getInstance(requireContext())?.namePlayer = playerNameInput
                    hideKeyboard(btnStart)
                    modeDialog()
                } else {
                    Toast.makeText(requireContext(), "Please enter your name", Toast.LENGTH_LONG).show()
                }
                dialog.dismiss()
            }

            dialog.setView(dialogBinding)
            dialog.show()
        }
    }
    private var isModeSelected = false
    override fun modeDialog(){
            val dialog = AlertDialog.Builder(requireContext()).create()
            val dialogBinding =
                LayoutInflater.from(requireContext()).inflate(R.layout.mode_dialog, null, false)

            dialog.setCancelable(false)
            dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)

            dialogBinding.easyButton.setOnClickListener {
                MySharedPreference.getInstance(requireContext())?.gameMode = "easy"
                isModeSelected = true
                (presenter as MainPresenterImpl).onClickPlay(requireView())
                dialog.dismiss()
            }

            dialogBinding.hardButton.setOnClickListener {
                MySharedPreference.getInstance(requireContext())?.gameMode = "hard"
                isModeSelected = true
                (presenter as MainPresenterImpl).onClickPlay(requireView())

                dialog.dismiss()
            }

            dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            dialog.setView(dialogBinding)
            dialog.show()
    }

    override fun infoDialog() {

        val dialog = AlertDialog.Builder(requireContext()).create()
        val dialogBinding =
            LayoutInflater.from(requireContext()).inflate(R.layout.info_dialog, container, false)

        dialog.setCancelable(false)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        dialogBinding.yes.setOnClickListener {

            dialog.cancel()
        }
        dialog.setView(dialogBinding)
        dialog.show()
    }

    private fun hideKeyboard(view: View) {
        val imm = view.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }


    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)

        text.text = requireContext().resources.getString(R.string.game_name)
        play.text = requireContext().resources.getString(R.string.play)
        play_info.text = requireContext().resources.getString(R.string.rule)
    }
}