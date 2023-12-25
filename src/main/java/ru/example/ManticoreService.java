package ru.example;

import com.manticoresearch.client.ApiException;
import com.manticoresearch.client.model.InsertDocumentRequest;
import com.manticoresearch.client.model.SearchRequest;
import com.manticoresearch.client.model.SearchResponse;
import com.manticoresearch.client.model.SuccessResponse;

import java.util.List;

public class ManticoreService {

    private SearchCore core;

    public ManticoreService(SearchCore core){
        this.core = core;
    }

    public List<Object> executeCommand(String sqlCommand) {
        try {
            return core.getUtils().sql(sqlCommand,true);
        } catch (ApiException e) {
            throw new RuntimeException(e);
        }
    }

    public SuccessResponse insertInTable(InsertDocumentRequest doc) {
        try {
            return core.getIndex().insert(doc);
        } catch (ApiException e) {
            throw new RuntimeException(e);
        }
    }

    public SearchResponse search(SearchRequest searchRequest) {
        try {
            return core.getSearch().search(searchRequest);
        } catch (ApiException e) {
            throw new RuntimeException(e);
        }
    }
}
