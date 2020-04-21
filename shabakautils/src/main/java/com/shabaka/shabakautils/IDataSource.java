package com.shabaka.shabakautils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Completable;
import io.reactivex.Observable;

public interface IDataSource<T> {

    /**
     * get all items
     * @return
     */
    Observable<List<T>> getAll();

    /**
     * get all items with query request
     * @param query
     * @return
     */
    Observable<List<T>> getAll(IDataSource.Query<T> query);
    /**
     * get all items with paged list
     * @return
     */
    Observable<DjangoRestFrameworkPagedResponse<T>> getAllPaged();
    /**
     * get all items with paged list with query
     * @param query
     * @return
     */
    Observable<DjangoRestFrameworkPagedResponse<T>> getAllPaged(IDataSource.Query<T> query);

    /**
     * get item by id
     * @param id
     * @return
     */
    Observable<T> getItemById(String id);//OR URL check best solution with django

    /**
     * Save list of item
     * @param list
     * @return
     */
    Observable<List<T>> saveAll(List<T> list);

    /**
     * Save a giving item
     * @param item
     * @return
     */
    Observable<T> saveItem(T item);

    /**
     * Remove list of items
     * @param list
     * @return
     */
    Completable remove(List<T> list);

    /**
     * Remove All items
     * @return
     */
    Completable removeAll();

    /**
     * Query default initialize
     * @return
     */
    default Query<T> query(){
        return new Query(this);
    }





    class Query<T> {

        IDataSource<T> dataSource;
        public Query(IDataSource<T> dataSource) {
            this.dataSource = dataSource;
        }
        private Map<String, String> params = new HashMap<>();
        public boolean has(String property){
            return params.get(property) != null;
        }
        public String get(String property){
            return params.get(property);
        }
        public Query<T> where(String property, String value){
            params.put(property, value);
            return this;
        }

        public Observable<List<T>> findAll(){
            return dataSource.getAll(this);
        }
        public Observable<T> findFirst(){
            return dataSource.getAll(this)
                    .filter(t->!t.isEmpty()).map(t->t.get(0));
        }


    }
}
