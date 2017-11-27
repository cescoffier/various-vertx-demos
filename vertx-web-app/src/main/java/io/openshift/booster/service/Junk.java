package io.openshift.booster.service;

import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import rx.Single;

/**
 * @author <a href="http://escoffier.me">Clement Escoffier</a>
 */
public class Junk {

    private void call_service(int a, int b, Handler<AsyncResult<Integer>> resultHandler) {

    }

    public Single<Integer> call_service(int a, int b) {
        return null;
    }

    public void call_1() {
        call_service(1, 2, ar -> {
           int res = ar.result();
        });

        Single<Integer> single = call_service(1, 2);
        single.subscribe(
            res -> {

            }
        );
    }


}
