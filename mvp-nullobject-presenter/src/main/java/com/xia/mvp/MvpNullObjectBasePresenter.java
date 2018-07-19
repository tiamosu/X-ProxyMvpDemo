package com.xia.mvp;

import android.support.annotation.NonNull;
import android.support.annotation.UiThread;

import java.lang.ref.WeakReference;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * @author xia
 * @date 2018/7/19.
 */
public abstract class MvpNullObjectBasePresenter<V extends MvpView> implements MvpPresenter<V> {
    private WeakReference<V> view;
    private final V nullView;
    private boolean viewAttachedAtLeastOnce = false;

    @SuppressWarnings("unchecked")
    public MvpNullObjectBasePresenter() {
        try {
            // Scan the inheritance hierarchy until we reached MvpNullObjectBasePresenter
            Class<V> viewClass = null;
            Class<?> currentClass = getClass();

            while (viewClass == null) {
                Type genericSuperType = currentClass.getGenericSuperclass();
                while (!(genericSuperType instanceof ParameterizedType)) {
                    // Scan inheritance tree until we find ParameterizedType which is probably a MvpSubclass
                    currentClass = currentClass.getSuperclass();
                    genericSuperType = currentClass.getGenericSuperclass();
                }

                final Type[] types = ((ParameterizedType) genericSuperType).getActualTypeArguments();
                for (Type type : types) {
                    Class<?> genericType = (Class<?>) type;
                    if (genericType.isInterface() && isSubTypeOfMvpView(genericType)) {
                        viewClass = (Class<V>) genericType;
                        break;
                    }
                }
                // Continue with next class in inheritance hierarchy (see genericSuperType assignment at start of while loop)
                currentClass = currentClass.getSuperclass();
            }
            nullView = NoOp.of(viewClass);
        } catch (Throwable t) {
            throw new IllegalArgumentException(
                    "The generic type <V extends MvpView> must be the first generic type argument of class "
                            + getClass().getSimpleName()
                            + " (per convention). Otherwise we can't determine which type of View this"
                            + " Presenter coordinates.", t);
        }
    }

    /**
     * Scans the interface inheritance hierarchy and checks if on the root is MvpView.class
     *
     * @param cls The leaf interface where to begin to scan
     * @return true if subtype of MvpView, otherwise false
     */
    private boolean isSubTypeOfMvpView(Class<?> cls) {
        if (cls.equals(MvpView.class)) {
            return true;
        }
        final Class[] superInterfaces = cls.getInterfaces();
        for (Class superInterface : superInterfaces) {
            if (isSubTypeOfMvpView(superInterface)) {
                return true;
            }
        }
        return false;
    }

    @UiThread
    @Override
    public void attachView(@NonNull V view) {
        this.view = new WeakReference<>(view);
        viewAttachedAtLeastOnce = true;
    }

    @UiThread
    @NonNull
    protected V getView() {
        if (!viewAttachedAtLeastOnce) {
            throw new IllegalStateException("No view has ever been attached to this presenter!");
        }
        if (view != null) {
            V realView = view.get();
            if (realView != null) {
                return realView;
            }
        }
        return nullView;
    }

    @Override
    @UiThread
    public void detachView() {
        if (view != null) {
            view.clear();
            view = null;
        }
    }

    @Override
    @UiThread
    public void destroy() {
    }
}
