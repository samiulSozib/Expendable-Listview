package com.example.expandablelistview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ExpandableListView expandableListView;
    private CustomAdapter customAdapter;
    List<String> listDataHeader;
    HashMap<String,List<String>> listChildHeader;
    private int lastexpandablelist=-1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        prepareData();
        expandableListView=findViewById(R.id.expandablelistId);
        customAdapter=new CustomAdapter(this,listDataHeader,listChildHeader);
        expandableListView.setAdapter(customAdapter);


        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView expandableListView, View view, int i, long l) {
                String groupName=listDataHeader.get(i);
                Toast.makeText(getApplicationContext(),groupName,Toast.LENGTH_SHORT).show();

                return false;
            }
        });


        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int i) {
                if(lastexpandablelist!=-1&&lastexpandablelist!=i){
                    expandableListView.collapseGroup(lastexpandablelist);
                }
                lastexpandablelist=i;
            }
        });
    }

    public void prepareData(){
        final String[] header_string=getResources().getStringArray(R.array.group_data);
        String[] child_string=getResources().getStringArray(R.array.child_data);

        listDataHeader=new ArrayList<>();
        listChildHeader=new HashMap<>();

        for (int i=0;i<header_string.length;i++){
            //adding header
            listDataHeader.add(header_string[i]);

            List<String> child=new ArrayList<>();
            child.add(child_string[i]);
            listChildHeader.put(listDataHeader.get(i),child);

        }
    }
}
