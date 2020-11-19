package com.example.topmovies.NetworkIU.Response;

import com.squareup.moshi.Json;

import java.util.List;

public class ResultReview {
    @Json(name = "results")
    private final List<ResponseReview> resultadoreviews;

    public ResultReview(List<ResponseReview> resultadoreviews) {
        this.resultadoreviews=resultadoreviews;
    }

    public List<ResponseReview> getReviews() {
        return resultadoreviews;
    }
}
