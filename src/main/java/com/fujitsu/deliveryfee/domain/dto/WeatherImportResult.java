package com.fujitsu.deliveryfee.domain.dto;

public class WeatherImportResult {

    private int total;
    private int successful;
    private int failed;

    public WeatherImportResult() {}

    public WeatherImportResult(int total, int successful, int failed) {
        this.total = total;
        this.successful = successful;
        this.failed = failed;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getSuccessful() {
        return successful;
    }

    public void setSuccessful(int successful) {
        this.successful = successful;
    }

    public int getFailed() {
        return failed;
    }

    public void setFailed(int failed) {
        this.failed = failed;
    }
}