package mchehab.com.googlemapsmarkergrouping

import android.content.Context
import com.google.android.gms.maps.GoogleMap
import com.google.maps.android.clustering.Cluster
import com.google.maps.android.clustering.ClusterItem
import com.google.maps.android.clustering.ClusterManager
import com.google.maps.android.clustering.view.DefaultClusterRenderer


class MarkerClusterRenderer<T : ClusterItem> internal constructor(
    context: Context,
    googleMap: GoogleMap,
    clusterManager: ClusterManager<T>
) : DefaultClusterRenderer<T>(context, googleMap, clusterManager) {

    override fun shouldRenderAsCluster(cluster: Cluster<T>): Boolean {
        return cluster.size >= 1
    }
}