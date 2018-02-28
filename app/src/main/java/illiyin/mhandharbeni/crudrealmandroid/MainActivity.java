package illiyin.mhandharbeni.crudrealmandroid;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import illiyin.mhandharbeni.crudrealmmodul.CRUDRealm;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {
    CRUDRealm crudRealm;
    SampleModule sampleModule;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sampleModule = new SampleModule();
        crudRealm = new CRUDRealm(this, sampleModule);
        setContentView(R.layout.activity_main);

        insert();
        viewInsert();
//        update();
        viewLike();
    }

    public void insert(){
        RealmResults results = crudRealm.read();
        Integer id_total = results.size() + 1;
        SampleModule addModule = new SampleModule();
        addModule.setId(id_total);
        addModule.setNama("Beni");
        addModule.setAlamat("Malang");
        crudRealm.create(addModule);

        id_total += 1;
        SampleModule addModules = new SampleModule();
        addModules.setId(id_total);
        addModules.setNama("Besni");
        addModules.setAlamat("Malang");
        crudRealm.create(addModules);

        id_total += 1;
        SampleModule addModulex = new SampleModule();
        addModulex.setId(id_total);
        addModulex.setNama("Benis");
        addModulex.setAlamat("Malang");
        crudRealm.create(addModulex);

        id_total += 1;
        SampleModule addModuley = new SampleModule();
        addModuley.setId(id_total);
        addModuley.setNama("ima");
        addModuley.setAlamat("Malang");
        crudRealm.create(addModuley);

    }
    public void update(){
        crudRealm.openObject();
        SampleModule updateModule = (SampleModule) crudRealm.setObjectUpdate("id", 1);
        updateModule.setNama("BENI2");
        updateModule.setAlamat("MALANG2");
        crudRealm.update(updateModule);
        crudRealm.commitObject();
    }

    public void viewInsert(){
        Integer[] id = {1, 2};
        RealmResults results = crudRealm.read("id", id);
        sampleModule = (SampleModule) results.get(0);
        String charSequence = sampleModule.getNama();
        charSequence += sampleModule.getAlamat();
        charSequence += sampleModule.getId();
        TextView txtInsert = (TextView) findViewById(R.id.insert);
        txtInsert.setText(charSequence);
    }

    public void viewUpdate(){
        RealmResults results = crudRealm.read();
        sampleModule = (SampleModule) results.get(0);
        String charSequence = sampleModule.getNama();
        charSequence += sampleModule.getAlamat();
        charSequence += sampleModule.getId();
        TextView txtInsert = (TextView) findViewById(R.id.update);
        txtInsert.setText(charSequence);
    }
    public void viewLike(){
        String charSequence = "";
        RealmResults results = crudRealm.contains("nama", "ma");
        for (int i = 0 ;i<results.size();i++){
            SampleModule sampleModules = (SampleModule) results.get(i);
            charSequence += sampleModules.getNama();
            charSequence += sampleModules.getAlamat();
            charSequence += sampleModules.getId();
        }
        TextView txtInsert = (TextView) findViewById(R.id.update);
        txtInsert.setText(charSequence);
    }
    @Override
    protected void onDestroy() {
        crudRealm.closeRealm();
        super.onDestroy();
    }
}
