package social.com.locationtutorial;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
public class Main2Activity extends AppCompatActivity {
    DatabaseReference mDatabaseReference;
    ListView listView;
    FirebaseListAdapter<Model> modelFirebaseListAdapter;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        button= (Button) findViewById(R.id.button);
        FirebaseApp.initializeApp(this);
        listView= (ListView) findViewById(R.id.listView);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(Main2Activity.this,MainActivity.class);
                startActivity(i);
            }
        });
        mDatabaseReference = FirebaseDatabase.getInstance().getReference();

        modelFirebaseListAdapter=new FirebaseListAdapter<Model>(this,Model.class,R.layout.list_item,mDatabaseReference) {
            @Override
            protected void populateView(View view, final Model model, int i) {
                Log.d("Chat",model.DeviceId);
                 TextView textView=((TextView)view.findViewById(R.id.text1));
                textView.setText("Device id:"+model.DeviceId);
                textView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent i=new Intent(Main2Activity.this,MapsActivity.class);
                        i.putExtra("lat",model.mLatitude);
                        i.putExtra("long",model.mLongitude);
                        startActivity(i);
                    }
                });
                ((TextView)view.findViewById(R.id.text2)).setText("Latitude:"+model.mLatitude+"  "+"Longitude:"+model.mLongitude);
            }


        };
        listView.setAdapter(modelFirebaseListAdapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        modelFirebaseListAdapter.cleanup();
    }
}
