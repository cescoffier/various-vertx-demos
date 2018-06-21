package io.vertx.example.service;

import io.vertx.core.json.JsonObject;
import rx.Completable;
import rx.Observable;
import rx.Single;

/**
 * A CRUD to SQL interface
 */
public interface Store {

  Single<JsonObject> create(JsonObject item);

  Observable<JsonObject> getAll();

  Single<JsonObject> get(long id);

  Completable update(long id, JsonObject item);

  Completable delete(long id);
}
