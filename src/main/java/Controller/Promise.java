package Controller;

import Model.PromiseState;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Promise<ResolveType> {
    //Successful callback function passed by the user
    public interface ResolveCallBack<ResolveType> {
        void onResolve(ResolveType res);
    }

    //Failed callback function passed in by the user
    public interface RejectCallBack {
        void onReject(Exception e);
    }

    //Return value function passed to the user
    public interface OnResolved<ResolveType> {
        void resolve(ResolveType resolveType);
    }

    //Exception function passed to the user
    public interface OnRejected {
        void reject(Exception e);
    }

    public interface PromiseFunc<ResolveType> {
        void promise(OnResolved<ResolveType> resolve, OnRejected reject);
    }


    private PromiseState promiseState;
    private ResolveType promiseRes;
    private Exception promiseErr;
    private final Queue<ResolveCallBack<ResolveType>> resolveCallBacks = new ArrayDeque<>();
    private final Queue<RejectCallBack> rejectCallBacks = new ArrayDeque<>();
    private final Lock lock = new ReentrantLock();

    public Promise(PromiseFunc<ResolveType> promiseFunc) {
        promiseState = PromiseState.pending;
        OnResolved<ResolveType> onResolved = (res) -> {
            lock.lock();
            try {
                if (promiseState == PromiseState.pending) {
                    promiseState = PromiseState.fulfilled;
                    promiseRes = res;
                    resolveCallBacks.forEach((resolveTypeResolveCallBack -> resolveTypeResolveCallBack.onResolve(promiseRes)));
                }
            } finally {
                lock.unlock();
            }
        };
        OnRejected onRejected = (err) -> {
            lock.lock();
            try {
                if (promiseState == PromiseState.pending) {
                    promiseState = PromiseState.rejected;
                    promiseErr = err;
                    rejectCallBacks.forEach(rejectCallBack -> rejectCallBack.onReject(promiseErr));
                }
            } finally {
                lock.unlock();
            }
        };
        try {
            new Thread(() -> {
                promiseFunc.promise(onResolved, onRejected);
            }).start();
        } catch (Exception e) {
            onRejected.reject(e);
        }

    }

    public Promise<ResolveType> then(ResolveCallBack<ResolveType> resolveCallBack, RejectCallBack rejectCallBack) {
        lock.lock();
        try {
            if (promiseState == PromiseState.pending) {
                if (resolveCallBack != null) resolveCallBacks.add(resolveCallBack);
                if (rejectCallBack != null) rejectCallBacks.add(rejectCallBack);
            } else {
                if (promiseRes != null && resolveCallBack != null) resolveCallBack.onResolve(promiseRes);
                if (promiseErr != null && rejectCallBack != null) rejectCallBack.onReject(promiseErr);
            }
            return this;
        } finally {
            lock.unlock();
        }
    }

    public Promise<ResolveType> then(ResolveCallBack<ResolveType> resolveCallBack) {
        then(resolveCallBack, null);
        return this;
    }

    public Promise<ResolveType> catchE(RejectCallBack rejectCallBack) {
        then(null, rejectCallBack);
        return this;
    }

}

