package com.example.earthquake2
//fatma
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import jsondata.Features
import java.math.RoundingMode
import java.util.*


class EarthFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: EAdapter
    private lateinit var EviewModel:EarthViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        EviewModel = ViewModelProviders.of(this).get(EarthViewModel::class.java)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_earth, container, false)
        recyclerView = view.findViewById(R.id.quake_recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(context)
        return view

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        EviewModel.quakesLiveData.observe(
            viewLifecycleOwner,
            Observer { Features ->
//                Toast.makeText(context, features[0].id, Toast.LENGTH_LONG).show()
                updateUI(Features)
            })
    }

    private fun updateUI(Features: List<Features>) {
        adapter = EAdapter(Features)
        recyclerView.adapter = adapter
    }

    companion object {
        @JvmStatic
        fun newInstance() = EarthFragment()
    }

    private class EHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        private val magView = itemView.findViewById(R.id.mag) as TextView
        private val countryView = itemView.findViewById(R.id.country) as TextView
        private val placeView = itemView.findViewById(R.id.place) as TextView
        private val dateView = itemView.findViewById(R.id.date) as TextView
        private val timeView = itemView.findViewById(R.id.time) as TextView


        override fun onClick(v: View?) {
            TODO("Not yet implemented")
        }

        fun bind(fe: Features) {

            // color
            magView.apply {

                text = fe.properties.mag.toString()

                var magView1: Double=fe.properties.mag

                when {
                    magView1 in 2.0..3.9 -> setBackgroundResource(R.drawable.shape1)
                    magView1 in 4.0..4.9 -> setBackgroundResource(R.drawable.shape2)
                    magView1 in 5.0..5.9 -> setBackgroundResource(R.drawable.shap4)
                    magView1 in 6.0..10.9 -> setBackgroundResource(R.drawable.shape3)
                }
            }

            //place
            placeView.text = fe.properties.place

            //date
            val calendar = Calendar.getInstance()
            calendar.time = Date(fe.properties.time)
            val date = "${calendar.get(Calendar.YEAR)}-" +
                    "${calendar.get(Calendar.MONTH)}-" +
                    "${calendar.get(Calendar.DAY_OF_MONTH)}"
                     dateView.text = date

            //time
            val time = "${calendar.get(Calendar.HOUR_OF_DAY)}:" +
                    "${calendar.get(Calendar.MINUTE)}"
                   timeView.text = time

    }

    }

    private class EAdapter(private val PropertiesItems: List<Features>) :
        RecyclerView.Adapter<EHolder>() {
        override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
        ): EHolder {
            val view =
                LayoutInflater.from(parent.context).inflate(R.layout.earth_list, parent, false)
            return EHolder(view)
        }

        override fun getItemCount(): Int = PropertiesItems.size

        override fun onBindViewHolder(holder: EHolder, position: Int) {
            val features = PropertiesItems[position]
            holder.bind(features)
        }
    }

}