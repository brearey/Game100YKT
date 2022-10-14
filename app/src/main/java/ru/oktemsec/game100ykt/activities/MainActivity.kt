package ru.oktemsec.game100ykt.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentResultListener
import androidx.lifecycle.LifecycleOwner
import ru.oktemsec.game100ykt.R
import ru.oktemsec.game100ykt.databinding.ActivityMainBinding
import ru.oktemsec.game100ykt.fragments.HelpFragment
import ru.oktemsec.game100ykt.fragments.MainFragment
import ru.oktemsec.game100ykt.utils.Navigator
import ru.oktemsec.game100ykt.utils.Options
import ru.oktemsec.game100ykt.utils.ResultListener

class MainActivity : AppCompatActivity(), Navigator {

    private lateinit var binding: ActivityMainBinding

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

    // открыть фрагмент "Правила игры"
    override fun showHelpScreen() {
        launchFragment(HelpFragment())
    }

    override fun showBoxSelectionScreen(options: Options) {
        // переделать в новое окно
    }

    override fun showOptionsScreen(options: Options) {
        // переделать в новое окно
    }

    override fun showCongratulationsScreen() {
        // переделать в новое окно
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

    // запуск какого-либо фрагмента
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