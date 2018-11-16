package mchehab.com.java;

import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.clustering.ClusterItem;

public class MarkerClusterItem implements ClusterItem {

    private LatLng latLng;
    private String title;

    public MarkerClusterItem(LatLng latLng, String title){
        this.latLng = latLng;
        this.title = title;
    }

    @Override
    public LatLng getPosition() {
        return latLng;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String getSnippet() {
        return "";
    }
}