package com.phunware.android.phunwareproducthomework.features.detail.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.phunware.android.phunwareproducthomework.R
import com.phunware.android.phunwareproducthomework.features.detail.ZipCodeDetailViewModel
import com.phunware.android.weathersdk.models.Weather
import kotlinx.android.synthetic.main.fragment_detail.*
import java.util.*


class DetailFragment : Fragment() {
    companion object {
        fun newInstance(zipCode: String): DetailFragment {
            return DetailFragment().apply { arguments = DetailFragmentArgs.Builder(zipCode).build().toBundle() }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View = inflater.inflate(R.layout.fragment_detail, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val zipCode = DetailFragmentArgs.fromBundle(arguments).zipCode

        loadingGroup.visibility = View.VISIBLE
        weatherGroup.visibility = View.GONE

        val model = ViewModelProviders.of(this)
                .get(ZipCodeDetailViewModel::class.java)

        model.getWeather(zipCode).observe(this, Observer<Weather> {
            zipCodeTextView.text = getString(R.string.detail_title, it.name, zipCode)
            currentTempTextView.text = it.temperatures.temp.toString()
            highTempTextView.text = it.temperatures.tempMax.toString()

            sunriseTextView.text = Date(it.locationInfo.sunrise).toString()
            sunsetTextView.text = Date(it.locationInfo.sunset).toString()


            loadingGroup.visibility = View.GONE
            weatherGroup.visibility = View.VISIBLE
        })
    }
}
