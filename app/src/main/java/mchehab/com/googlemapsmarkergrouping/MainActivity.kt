package mchehab.com.googlemapsmarkergrouping

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.maps.android.clustering.ClusterItem
import com.google.maps.android.clustering.ClusterManager
import org.json.JSONArray


class MainActivity : AppCompatActivity(), OnMapReadyCallback{

    private lateinit var googleMap: GoogleMap
    private lateinit var jsonArray: JSONArray
    private lateinit var clusterManager: ClusterManager<ClusterItem>
    private val markerList = mutableListOf<MarkerOptions>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        jsonArray = readAssets()

        val supportFragment = supportFragmentManager.findFragmentById(R.id.googleMap) as SupportMapFragment
        supportFragment.getMapAsync(this)
    }

    private fun readAssets(): JSONArray{
        val json = assets.open("places.json")
            .bufferedReader()
            .readText()
        return JSONArray(json)
    }

    private fun addMarkers(){
        for (index in 0 until jsonArray.length()){
            val jsonObject = jsonArray.getJSONObject(index)
            val name = jsonObject.getString("name")
            val lat = jsonObject.getDouble("lat")
            val lng = jsonObject.getDouble("lng")
            val marker = MarkerOptions().position(LatLng(lat, lng)).title(name)
            markerList.add(marker)
        }
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(markerList[0].position, 13.0f))
    }

    override fun onMapReady(googleMap: GoogleMap) {
        this.googleMap = googleMap
        clusterManager = ClusterManager(this, googleMap)
        addMarkers()
        setupClusterManager()
    }

    private fun setupClusterManager() {
        clusterManager.renderer = MarkerClusterRenderer(this, googleMap, clusterManager)
        addClusters()
        setClusterManagerClickListener()
        googleMap.setOnCameraIdleListener(clusterManager)
        googleMap.setOnMarkerClickListener(clusterManager)
        clusterManager.cluster()
    }

    private fun addClusters() {
        for (marker in markerList){
            val clusterItem = MarkerClusterItem(marker.position, marker.title)
            clusterManager.addItem(clusterItem)
        }
    }

    private fun setClusterManagerClickListener() {
        clusterManager.setOnClusterClickListener {
            val items = it.items
            val itemsList = mutableListOf<String>()
            for (item in items){
                itemsList.add((item as MarkerClusterItem).markerTitle)
            }
            googleMap.animateCamera(CameraUpdateFactory.newLatLng(it.position), object: GoogleMap.CancelableCallback{
                override fun onFinish() {
                    ListViewDialog(this@MainActivity, itemsList)
                }
                override fun onCancel() {}
            })
            true
        }
    }
}