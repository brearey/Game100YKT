package ru.oktemsec.game100ykt.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentResultListener
import androidx.lifecycle.LifecycleOwner
import ru.oktemsec.game100ykt.R
import ru.oktemsec.game100ykt.data.GameViewModel
import ru.oktemsec.game100ykt.databinding.ActivityMainBinding
import ru.oktemsec.game100ykt.dialogs.QuestionDialogFragment
import ru.oktemsec.game100ykt.fragments.*
import ru.oktemsec.game100ykt.utils.Navigator
import ru.oktemsec.game100ykt.utils.ResultListener

class MainActivity : AppCompatActivity(), Navigator {

    private lateinit var binding: ActivityMainBinding
    // ViewModel
    private val gameViewModel: GameViewModel by viewModels()

    private val currentFragment: Fragment
        get() = supportFragmentManager.findFragmentById(R.id.fragment_container)!!

    private val fragmentListener = object : FragmentManager.FragmentLifecycleCallbacks() {
        override fun onFragmentViewCreated(fm: FragmentManager, f: Fragment, v: View, savedInstanceState: Bundle?) {
            super.onFragmentViewCreated(fm, f, v, savedInstanceState)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).also { setContentView(it.root) }

        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .add(R.id.fragment_container, MainFragment())
                .commit()
        }

        supportFragmentManager.registerFragmentLifecycleCallbacks(fragmentListener, false)
    }

    override fun onDestroy() {
        super.onDestroy()
        supportFragmentManager.unregisterFragmentLifecycleCallbacks(fragmentListener)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    // ?????????????? ???????????????? "?????????????? ????????"
    override fun showHelpScreen() {
        launchFragment(HelpFragment())
    }

    override fun showPlayerScreen() {
        launchFragment(PlayerFragment())
    }

    override fun showVerseScreen() {
        launchFragment(VerseFragment())
    }

    override fun showInformationCard() {
        if (gameViewModel.itemsLeft() > 0) {
            launchFragment(InformationCardFragment(gameViewModel))
        }
        else {
            Toast.makeText(this, "???????????????????????????? ???????????????? ???????????? ??????", Toast.LENGTH_SHORT).show()
        }
    }

    override fun showQuestionDialog(reward: Int) {
        if ((gameViewModel.availableQuestionsList.value?.size ?: 0) > 0) {
            val questionDialogFragment = QuestionDialogFragment()
            questionDialogFragment.show(supportFragmentManager, reward = reward)
        }
        else {
            Toast.makeText(this, "???????????????? ???????????? ??????", Toast.LENGTH_SHORT).show()
        }
    }


    override fun goBack() {
        onBackPressed()
    }

    override fun goToMenu() {
        supportFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
    }

    override fun <T : Parcelable> publishResult(result: T) {
        supportFragmentManager.setFragmentResult(result.javaClass.name, bundleOf(KEY_RESULT to result))
    }

    override fun <T : Parcelable> listenResult(
        clazz: Class<T>,
        owner: LifecycleOwner,
        listener: ResultListener<T>
    ) {
        supportFragmentManager.setFragmentResultListener(clazz.name, owner, FragmentResultListener { key, bundle ->
            listener.invoke(bundle.getParcelable(KEY_RESULT)!!)
        })
    }

    // ???????????? ????????????-???????? ??????????????????
    private fun launchFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .setCustomAnimations(
                R.anim.slide_in,
                R.anim.fade_out,
                R.anim.fade_in,
                R.anim.slide_out
            )
            .addToBackStack(null)
            .replace(R.id.fragment_container, fragment)
            .commit()
    }

    companion object {
        @JvmStatic private val KEY_RESULT = "RESULT"
    }
}