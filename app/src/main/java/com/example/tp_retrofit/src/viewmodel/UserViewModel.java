package com.example.tp_retrofit.src.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.tp_retrofit.src.model.User;
import com.example.tp_retrofit.src.repository.UserRepository;

import java.util.List;

public class UserViewModel extends ViewModel {
    private final MutableLiveData<List<User>> _users = new MutableLiveData<>();
    private final LiveData<List<User>> users = _users;
    private final MutableLiveData<Throwable> _error = new MutableLiveData<>();
    private final UserRepository repository;

    public UserViewModel(UserRepository repository) {
        this.repository = repository;
        loadUsers();
    }

    public LiveData<List<User>> getUsers() {
        return users;
    }

    public LiveData<Throwable> getError() {
        return _error;
    }

    public void refreshUsers() {
        loadUsers();
    }

    private void loadUsers() {
        repository.fetchUsers(new UserRepository.UserCallback() {
            @Override
            public void onSuccess(List<User> userList) {
                _users.postValue(userList);
                _error.postValue(null); // Clear previous errors on success
            }

            @Override
            public void onError(Throwable error) {
                _error.postValue(error);
                _users.postValue(null); // Clear previous data on error
            }
        });
    }
}