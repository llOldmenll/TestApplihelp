package com.oldmen.testapplihelp.data.network;

import com.oldmen.testapplihelp.domain.models.Board;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiService {

    @GET("boards")
    Observable<List<Board>> getBoards();

    @POST("boards")
    Observable<Board> postBoard(@Body Board board);
}
