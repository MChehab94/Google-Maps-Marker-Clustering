package mchehab.com.googlemapsmarkergrouping

import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.clustering.ClusterItem

class MarkerClusterItem(val latLng: LatLng, val markerTitle: String = ""): ClusterItem{
    override fun getSnippet(): String {
        return ""
    }

    override fun getTitle(): String {
        return markerTitle
    }

    override fun getPosition(): LatLng {
        return latLng
    }
}