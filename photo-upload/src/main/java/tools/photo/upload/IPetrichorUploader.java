package tools.photo.upload;

import petrichor.entities.FileEntity;
import petrichor.entities.PhotoEntity;
import petrichor.entities.TableEntity;
import petrichor.entities.TableFieldRequest;
import petrichor.network.GCollectionType;
import petrichor.network.GMethod;
import petrichor.network.GPath;
import petrichor.network.IGEntity;

import java.io.File;
import java.util.Collection;
import java.util.List;
import java.util.Map;

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

    @GPath("/business/{}/json")
    @GMethod(GMethod.MethodName.GET)
    <T> Collection<T> getObjectList(String objectName, @GCollectionType Class<T> collectionType);

    @GPath("/business/{}/json")
    @GMethod(GMethod.MethodName.POST)
    void addBusinessObject(IGEntity entity, String objectName);

    @GPath("/file/upload")
    FileEntity upload(File file2upload);
}
