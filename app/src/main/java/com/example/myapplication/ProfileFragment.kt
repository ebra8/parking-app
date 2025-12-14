package com.example.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import com.example.myapplication.ui.theme.MyApplicationTheme
import com.google.firebase.auth.FirebaseAuth

class ProfileFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                MyApplicationTheme {
                    val user = FirebaseAuth.getInstance().currentUser
                    val displayName = user?.displayName ?: user?.email ?: "Guest"

                    ProfileScreen(
                        username = displayName,
                        onBackClick = {
                            parentFragmentManager.popBackStack()
                        },
                        onLogoutClick = {
                            FirebaseAuth.getInstance().signOut()
                            activity?.finish()
                        },
                        // --- FIX IS HERE ---
                        onSettingsClick = {
                            // Load the SettingsFragment into the same container
                            parentFragmentManager.beginTransaction()
                                .replace(R.id.fragment_container, SettingsFragment())
                                .addToBackStack(null)
                                .commit()
                        }
                        // -------------------
                    )
                }
            }
        }
    }
}