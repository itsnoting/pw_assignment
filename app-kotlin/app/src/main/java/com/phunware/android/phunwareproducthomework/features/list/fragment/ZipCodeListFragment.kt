package com.phunware.android.phunwareproducthomework.features.list.fragment

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.phunware.android.phunwareproducthomework.R
import com.phunware.android.phunwareproducthomework.features.list.ZipCodeListViewModel
import com.phunware.android.phunwareproducthomework.features.list.adapter.ZipCodeAdapter
import kotlinx.android.synthetic.main.fragment_zip_code_list.*

class ZipCodeListFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: ZipCodeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View = inflater.inflate(R.layout.fragment_zip_code_list, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewAdapter = ZipCodeAdapter(null)

        recyclerView = zipCodesRecyclerView.apply {
            setHasFixedSize(true)

            layoutManager = LinearLayoutManager(requireContext())

            adapter = viewAdapter
        }

        val model = ViewModelProviders.of(this)
                .get(ZipCodeListViewModel::class.java)
        model.getZipCodes().observe(this, Observer<List<String>> {
            viewAdapter.items = it
            viewAdapter.notifyDataSetChanged()
        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_search, menu)
        val searchViewItem = menu.findItem(R.id.action_search)
        val searchView = searchViewItem.actionView as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchView.clearFocus()

                val action = ZipCodeListFragmentDirections.AddZipCode(query!!)

                Navigation.findNavController(view!!).navigate(
                        action)

                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

        })
        super.onCreateOptionsMenu(menu, inflater)
    }
}
