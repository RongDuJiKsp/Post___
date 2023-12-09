package Controller;

public class Promise<ResolveType> {
    public interface ResolveCallBack<ResolveType> {
        void onResolve(ResolveType res);
    }


    public interface RejectCallBack {
        void onReject(Exception e);
    }

    public interface OnResolved<ResolveType> {
        void resolve(ResolveType resolveType);
    }

    public interface OnRejected {
        void reject(Exception e);
    }

    public interface PromiseFunc {
        void promise(OnResolved resolve, OnRejected reject);
    }


    PromiseState promiseState;
    ResolveType promiseRes;
    Exception promiseErr;
    ResolveCallBack resolveCallBack;
    RejectCallBack rejectCallBack;

    Promise(PromiseFunc promiseFunc) {
        promiseState = PromiseState.pending;
        OnResolved onResolved = (res) -> {
            if (promiseState == PromiseState.pending) {
                promiseState = PromiseState.fulfilled;
                promiseRes = (ResolveType) res;
                resolveCallBack.onResolve(res);
            }
        };
        OnRejected onRejected = (err) -> {
            if (promiseState == PromiseState.pending) {
                promiseState = PromiseState.rejected;
                promiseErr = err;
                rejectCallBack.onReject(err);
            }
        };
        try {
            promiseFunc.promise(onResolved, onRejected);
        } catch (Exception e) {
            onRejected.reject(e);
        }

    }
}

enum PromiseState {
    pending, fulfilled, rejected;
}