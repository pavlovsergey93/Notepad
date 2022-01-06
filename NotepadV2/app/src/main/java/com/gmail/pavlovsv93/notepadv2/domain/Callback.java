package com.gmail.pavlovsv93.notepadv2.domain;

public interface Callback<T> {

    void onSucces(T result);

    void onError(Throwable error);
}
