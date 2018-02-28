# CRUD Realm Android
### Installation
Add this to your build.gradle

```sh
compile 'illiyin.mhandharbeni.crudrealmmodul:crudrealmmodul:0.0.1'
```

### How to use

| Function | Parameter |
| ------ | ------ |
| Create | N/A |
| Read | N/A |
| Update | N/A |
| Delete | N/A |

### Sample
Create your model and extend it to RealmObject
```sh
public class TestModelObject extends RealmObject {
    String id, nama, alamat;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }
}
```

And in your activity
```sh
TestModelObject testModelObject = new TestModelObject();
CRUDRealm crudRealm = new CRUDRealm(getApplicationContext(), testModelObject);
```

sample adding row
```sh
    testModelObject = new TestModelObject();
    testModelObject.setId("1");
    testModelObject.setNama("Muhammad Handarbeni");
    testModelObject.setAlamat("Malang");
    crudRealm.create(testModelObject);
```

getting row
```sh
RealmResults resultRealm = crudRealm.read("id", "1");
if (resultRealm.size() > 0){
    for (int i=0;i<resultRealm.size();i++){
        //cast resultRealm to your model object to get the field
        testModelObject = (TestModelObject) resultRealm.get(i);
        Log.d(TAG, "ResulRealm: "+testModelObject.getNama());
    }
}
```        
