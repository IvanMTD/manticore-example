package ru.example;

import com.manticoresearch.client.ApiException;
import com.manticoresearch.client.model.*;

import java.util.List;
import java.util.Map;

public class ManticoreController {

    private String tableName;
    private ManticoreService service;

    public ManticoreController(SearchCore core){
        service = new ManticoreService(core);
    }

    /**
     * Описание параметров
     * @param tableName - название таблицы на пример some_table
     * @param tableParam - заключается в скобки (param1 type, param2 type, ...)
     * @param readResponse - если необходимо вывести в консоль информацию после обработки
     */
    public void createTable(String tableName, String tableParam, boolean readResponse){
        this.tableName = tableName;
        String sqlCommand = "create table if not exists " + tableName + tableParam + " morphology='stem_enru, libstemmer_ru'";
        List<Object> response = service.executeCommand(sqlCommand);
        if(readResponse){
            for(Object object : response){
                System.out.println(object.toString());
            }
        }
    }

    /**
     * Описание параметров
     * @param docMap - подготовленный документ по принципу key=value должно быть как в таблице кроме поля id
     * @param readResponse - если необходимо вывести в консоль информацию после обработки
     */
    public void insertData(Map<String,Object> docMap, boolean readResponse){
        InsertDocumentRequest doc = new InsertDocumentRequest();
        doc.index(tableName).doc(docMap);
        SuccessResponse response = service.insertInTable(doc);
        if(readResponse){
            System.out.println(response.toString());
        }
    }

    /**
     * Описание параметров
     * @param searchData - запрос поиска любые слова и предложения
     */
    public void search(String searchData){
        SearchRequest searchRequest = new SearchRequest();
        searchRequest.setIndex(tableName);
        QueryFilter queryFilter = new QueryFilter();
        queryFilter.setQueryString(searchData);
        searchRequest.setFulltextFilter(queryFilter);
        SearchResponse response = service.search(searchRequest);
        read(response);
    }

    private void read(SearchResponse response){
        System.out.println("Полный ответ:");
        System.out.println(response.toString());
        System.out.println("Детали:");
        for(String key : response.getAggregations().keySet()){
            System.out.println(key + " | " + response.getAggregations().get(key));
        }
    }

    public void update(){

    }

    public void delete(){

    }
}
