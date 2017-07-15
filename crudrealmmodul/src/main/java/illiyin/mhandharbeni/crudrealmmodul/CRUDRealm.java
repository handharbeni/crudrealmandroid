package illiyin.mhandharbeni.crudrealmmodul;

import android.content.Context;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmObject;
import io.realm.RealmResults;

/**
 * Created by root on 15/07/17.
 */

public class CRUDRealm {
    public Realm realm;
    public RealmObject modelObject;
    public Class<? extends RealmObject> objectModel;
    public Context context;

    public CRUDRealm(Context context, RealmObject objectModel) {
        this.context = context;
        this.modelObject = objectModel;
        this.objectModel = this.modelObject.getClass();
        Realm.init(context);
        try {
            realm = Realm.getDefaultInstance();
        } catch (Exception e) {
            RealmConfiguration config = new RealmConfiguration.Builder()
                    .deleteRealmIfMigrationNeeded()
                    .build();
            realm = Realm.getInstance(config);
        }
    }

    public void create(RealmObject objectModel) {
        realm.beginTransaction();
        realm.copyToRealm(objectModel);
        realm.commitTransaction();
    }

    public RealmResults<?> read(String filter, String change) {
        RealmResults<?> realmResult  = realm.where(modelObject.getClass()).equalTo(filter, change).findAll();
        return realmResult;
    }
    public boolean update(String filter, String value ,RealmObject objectUpdate){
        realm.beginTransaction();
        objectUpdate = realm.where(modelObject.getClass()).equalTo(filter, value).findFirst();
        realm.copyToRealmOrUpdate(objectUpdate);
        realm.commitTransaction();
        return true;
    }
    public boolean delete(String filter, String value) {
        realm.beginTransaction();
        RealmResults realmResults = realm.where(modelObject.getClass())
                .equalTo(filter, value)
                .findAll();
        boolean deleted = realmResults.deleteFirstFromRealm();
        realm.commitTransaction();
        return deleted;
    }
    public boolean checkDuplicate(String filter, String value) {
        RealmResults<?> realmResult = realm.where(modelObject.getClass())
                .equalTo(filter, value)
                .findAll();
        if (realmResult.size() > 0) {
            return true;
        } else {
            return false;
        }
    }

    public void closeRealm() {
    }
}
