package com.tsofen.sprint;

public interface WebserviceCallback {

    void onDataDownloadStarted();

    public void onDataReceived(String data);
}
