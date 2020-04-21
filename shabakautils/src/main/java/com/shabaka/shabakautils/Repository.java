package com.shabaka.shabakautils;

import android.app.Application;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

public class Repository<T> implements IDataSource<T>  {

    public IDataSource<T> api, db;
    private Application applicationContext;

    public Repository(Application applicationContext, IDataSource<T> api, IDataSource<T> db) {
        this.api = api;
        this.db = db;
        this.applicationContext = applicationContext;
    }


    @Override
    public Observable<List<T>> getAll() {
        if(Verificators.isNetworkAvailable(applicationContext))
            return Observable.defer(() -> api.getAllPaged()
                    .subscribeOn(Schedulers.io())
                    .flatMap(response ->  Observable.just(response.getResults())));

        return Observable.error(IllegalAccessError::new);
    }

    @Override
    public Observable<List<T>> getAll(Query<T> query) {
        if(Verificators.isNetworkAvailable(applicationContext))
            return Observable.defer(() -> api.getAllPaged(query)
                    .subscribeOn(Schedulers.io())
                    .flatMap(response ->  Observable.just(response.getResults())));
        return Observable.error(IllegalAccessError::new);
    }

    @Override
    public Observable<DjangoRestFrameworkPagedResponse<T>> getAllPaged() {
        if(Verificators.isNetworkAvailable(applicationContext))
            return Observable.defer(() -> api.getAllPaged()
                    .subscribeOn(Schedulers.io())
                    .flatMap(response ->  Observable.just(response)));
        return Observable.error(IllegalAccessError::new);
    }

    @Override
    public Observable<DjangoRestFrameworkPagedResponse<T>> getAllPaged(Query<T> query) {
        if(Verificators.isNetworkAvailable(applicationContext))
            return Observable.defer(() -> api.getAllPaged(query)
                    .subscribeOn(Schedulers.io())
                    .flatMap(response ->  Observable.just(response)));
        return Observable.error(IllegalAccessError::new);
    }

    @Override
    public Observable<T> getItemById(String id) {
        if(Verificators.isNetworkAvailable(applicationContext))
            return Observable.defer(() -> api.getItemById(id)
                    .subscribeOn(Schedulers.io())
                    .flatMap(Observable::just));

        return Observable.error(IllegalAccessError::new);
    }

    @Override
    public Observable<List<T>> saveAll(List<T> list) {
        if(Verificators.isNetworkAvailable(applicationContext))
            return Observable.defer(() -> api.saveAll(list)
                    .subscribeOn(Schedulers.io())
                    .flatMap(Observable::just));
        return Observable.error(IllegalAccessError::new);
    }


    @Override
    public Observable<T> saveItem(T item) {
        if(Verificators.isNetworkAvailable(applicationContext)){
            return Observable.defer(() -> api.saveItem(item).subscribeOn(Schedulers.io())
                    .flatMap(it -> db.saveItem(it)));
        }
        return Observable.error(IllegalAccessError::new);
    }

    @Override
    public Completable remove(List<T> list) {
        if(Verificators.isNetworkAvailable(applicationContext)){
            return Completable.defer(() -> api.remove(list).subscribeOn(Schedulers.io())
                    .andThen(db.remove(list)));
        }
        return Completable.error(IllegalAccessError::new);


    }

    @Override
    public Completable removeAll() {
        if(Verificators.isNetworkAvailable(applicationContext)){
            return Completable.defer(() -> api.removeAll().subscribeOn(Schedulers.io())
                    .andThen(db.removeAll()));
        }
        return Completable.error(IllegalAccessError::new);
    }
}
