package Controller;

import java.util.ArrayDeque;
import java.util.Queue;

public class Promise<ResolveType> {
    //用户传入的成功回调函数
    public interface ResolveCallBack<ResolveType> {
        void onResolve(ResolveType res);
    }

    //用户传入的失败回调函数
    public interface RejectCallBack {
        void onReject(Exception e);
    }

    //传给用户的返回值函数
    public interface OnResolved<ResolveType> {
        void resolve(ResolveType resolveType);
    }

    //传给用户的异常函数
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

    Promise(PromiseFunc<ResolveType> promiseFunc) {
        promiseState = PromiseState.pending;
        OnResolved<ResolveType> onResolved = (res) -> {
            if (promiseState == PromiseState.pending) {
                promiseState = PromiseState.fulfilled;
                promiseRes = res;
                resolveCallBacks.forEach((resolveTypeResolveCallBack -> {
                    resolveTypeResolveCallBack.onResolve(promiseRes);
                }));
            }
        };
        OnRejected onRejected = (err) -> {
            if (promiseState == PromiseState.pending) {
                promiseState = PromiseState.rejected;
                promiseErr = err;
                rejectCallBacks.forEach(rejectCallBack -> {
                    rejectCallBack.onReject(promiseErr);
                });
            }
        };
        try {
            promiseFunc.promise(onResolved, onRejected);
        } catch (Exception e) {
            onRejected.reject(e);
        }

    }

    public Promise<ResolveType> then(ResolveCallBack<ResolveType> resolveCallBack, RejectCallBack rejectCallBack) {
        resolveCallBacks.add(resolveCallBack);
        rejectCallBacks.add(rejectCallBack);
        return this;
    }

    public Promise<ResolveType> then(ResolveCallBack<ResolveType> resolveCallBack) {
        resolveCallBacks.add(resolveCallBack);
        return this;
    }

    public Promise<ResolveType> catchE(RejectCallBack rejectCallBack) {
        rejectCallBacks.add(rejectCallBack);
        return this;
    }

}

enum PromiseState {
    pending, fulfilled, rejected;
}