package tools.photo.upload;

import petrichor.entities.TableEntity;
import petrichor.entities.TableFieldRequest;
import petrichor.network.GMethod;
import petrichor.network.GPath;

/**
 * Created by Gregoire on 29/05/2018.
 */
public interface IPetrichorUploader {


    @GMethod(GMethod.MethodName.GET)
    @GPath("/object")
    TableEntity getTable(String tableName);

    @GPath("/object")
    @GMethod(GMethod.MethodName.POST)
    TableEntity createTable(String tableName);

    @GPath("/object")
    @GMethod(GMethod.MethodName.DELETE)
    void deleteTable(String name);

    @GPath("/field")
    @GMethod(GMethod.MethodName.POST)
    TableEntity addField(TableFieldRequest res, String tableName);
}
