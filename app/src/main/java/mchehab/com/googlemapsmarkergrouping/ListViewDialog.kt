package mchehab.com.googlemapsmarkergrouping

import android.app.Dialog
import android.content.Context
import android.view.LayoutInflater
import android.view.Window
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView

class ListViewDialog(context: Context, list: List<String>) {
    init{
        val dialog = Dialog(context)
        val view = LayoutInflater.from(context).inflate(R.layout.listview_dialog, null)
        val listView = view.findViewById<ListView>(R.id.listView)
        val button = view.findViewById<Button>(R.id.buttonDismiss)
        val adapter = ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, list)
        dialog.setCancelable(false)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        listView.adapter = adapter
        button.setOnClickListener {
            dialog.dismiss()
        }

        dialog.setContentView(view)
        dialog.show()
    }
}