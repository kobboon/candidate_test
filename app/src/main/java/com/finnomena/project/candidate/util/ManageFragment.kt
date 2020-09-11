package com.finnomena.project.candidate.util

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

object ManageFragment {

    fun addFragment(
        fragmentManager: FragmentManager,
        fragment: Fragment,
        content: Int, isBackStack: Boolean = true
    ) {
        val ft = fragmentManager.beginTransaction().add(content, fragment)
        if (isBackStack) ft.addToBackStack(null)
        ft.commit()
    }
}