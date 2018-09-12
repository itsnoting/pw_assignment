package com.phunware.android.phunwareproducthomework.features.add.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.phunware.android.phunwareproducthomework.R
import com.phunware.android.phunwareproducthomework.WeatherApp
import com.phunware.android.phunwareproducthomework.features.detail.fragment.DetailFragment
import com.phunware.android.phunwareproducthomework.storage.ZipCodeStore
import kotlinx.android.synthetic.main.fragment_add_zipcode.*
import javax.inject.Inject

class AddZipCodeFragment : Fragment() {
    @Inject
    lateinit var zipCodeStore: ZipCodeStore

    init {
        WeatherApp.getApplicationComponent().inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? = inflater.inflate(R.layout.fragment_add_zipcode, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val zipCode = AddZipCodeFragmentArgs.fromBundle(arguments).zipCode

        childFragmentManager.beginTransaction().replace(R.id.fragment_placeholder, DetailFragment.newInstance(zipCode), "frag_detail").commit()


        addZipCodeButton.setOnClickListener {
            zipCodeStore.addZipCode(zipCode)

            Toast.makeText(requireContext(), "$zipCode added.", Toast.LENGTH_SHORT).show()

            Navigation.findNavController(view).navigateUp()
        }

    }
}