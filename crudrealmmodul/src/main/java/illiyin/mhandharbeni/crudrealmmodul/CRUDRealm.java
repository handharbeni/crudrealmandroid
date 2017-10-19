package illiyin.mhandharbeni.crudrealmmodul;

import android.content.Context;

import io.realm.Case;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmObject;
import io.realm.RealmQuery;
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

    public RealmResults<?> read() {
        RealmResults<?> realmResult  = realm.where(modelObject.getClass()).findAll();
        return realmResult;
    }
    public RealmResults<?> read(String filter, String change) {
        RealmResults<?> realmResult  = realm.where(modelObject.getClass()).equalTo(filter, change).findAll();
        return realmResult;
    }
    public RealmResults<?> read(String filter, String[] change) {
        RealmQuery<?> realmResult  = realm.where(modelObject.getClass());
        int i = 0;
        for(String changes : change) {
            if(i != 0) {
                realmResult = realmResult.or();
            }
            realmResult = realmResult.equalTo(filter, changes);
            i++;
        }
        return realmResult.findAll();
    }
    public RealmResults<?> read(String filter, Integer change) {
        RealmResults<?> realmResult  = realm.where(modelObject.getClass()).equalTo(filter, change).findAll();
        return realmResult;
    }
    public RealmResults<?> like(String key, String filter, Case casing){
        RealmResults<?> realmResult  = realm.where(modelObject.getClass()).like(key, filter, casing).findAll();
        return realmResult;
    }
    public RealmResults<?> contains(String key, String filter, String sort){
        RealmResults<?> realmResults = realm.where(modelObject.getClass()).contains(key, filter).findAllSorted(sort);
        return realmResults;
    }
    public RealmResults<?> contains(String key, String filter){
        RealmResults<?> realmResults = realm.where(modelObject.getClass()).contains(key, filter, Case.INSENSITIVE).findAll();
        return realmResults;
    }
    public RealmResults<?> read(String filter, Integer[] change) {
        RealmQuery<?> realmResult  = realm.where(modelObject.getClass());
        int i = 0;
        for(Integer changes : change) {
            if(i != 0) {
                realmResult = realmResult.or();
            }
            realmResult = realmResult.equalTo(filter, changes);
            i++;
        }
        //.equalTo(filter, change).findAll();
        return realmResult.findAll();
    }
    public RealmObject setObjectUpdate(String filter, String value){
        RealmObject realmObject = realm.where(modelObject.getClass()).equalTo(filter, value).findFirst();
        return realmObject;
    }
    public RealmObject setObjectUpdate(String filter, Integer value){
        RealmObject realmObject = realm.where(modelObject.getClass()).equalTo(filter, value).findFirst();
        return realmObject;
    }
    public void openObject(){
        realm.beginTransaction();
    }
    public void commitObject(){
        realm.commitTransaction();
    }
    public boolean update(RealmObject objectUpdate){
        realm.copyToRealmOrUpdate(objectUpdate);
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
    public boolean delete(String filter, Integer value) {
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
        if (!realm.isClosed())
            realm.close();
    }
}
