package ariesvelasquez.com.githubrepobrowsermvvm.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import ariesvelasquez.com.githubrepobrowsermvvm.R
import ariesvelasquez.com.githubrepobrowsermvvm.utils.getViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val userRepoViewModel by lazy { getViewModel<UserRepositoryViewModel>() }

    private var sampleText = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Test
        userRepoViewModel.getUserRepos("ariesvelasquez").observe(this, Observer {
            if (it.status.isSuccessful()) {
                it.data?.forEachIndexed { index, userRepository ->
                    sampleText += userRepository.name
                    textViewTest.text = sampleText
                }
            }
        })
    }
}
