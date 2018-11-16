package mchehab.com.java;

import android.content.Context;
import com.google.android.gms.maps.GoogleMap;
import com.google.maps.android.clustering.Cluster;
import com.google.maps.android.clustering.ClusterItem;
import com.google.maps.android.clustering.ClusterManager;
import com.google.maps.android.clustering.view.DefaultClusterRenderer;

public class MarkerClusterRenderer<T extends ClusterItem> extends DefaultClusterRenderer<T> {

    public MarkerClusterRenderer(Context context, GoogleMap googleMap, ClusterManager<T> clusterManager){
        super(context, googleMap, clusterManager);
    }

    @Override
    protected boolean shouldRenderAsCluster(Cluster cluster) {
        return cluster.getSize() >= 1;
    }
}