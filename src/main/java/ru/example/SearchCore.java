package ru.example;

import com.manticoresearch.client.ApiClient;
import com.manticoresearch.client.Configuration;
import com.manticoresearch.client.api.IndexApi;
import com.manticoresearch.client.api.SearchApi;
import com.manticoresearch.client.api.UtilsApi;

public class SearchCore {
    private ApiClient client;

    private IndexApi index;
    private UtilsApi utils;
    private SearchApi search;

    public SearchCore(String path){
        client = Configuration.getDefaultApiClient();
        client.setBasePath(path);
        init();
    }

    private void init(){
        index = new IndexApi(client);
        utils = new UtilsApi(client);
        search = new SearchApi(client);
    }

    public IndexApi getIndex() {
        return index;
    }

    public UtilsApi getUtils() {
        return utils;
    }

    public SearchApi getSearch() {
        return search;
    }
}
