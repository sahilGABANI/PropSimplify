package com.propsimlify.app.ui.main.favourite

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.propsimlify.R
import com.propsimlify.app.base.BaseFragment
import com.propsimlify.app.ui.main.home.HomeFragment

class FavouriteFragment : BaseFragment() {

    companion object {
        @JvmStatic
        fun newInstance() = FavouriteFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_favourite, container, false)
    }

}