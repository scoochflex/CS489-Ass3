import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.sql.DataSource;

import javafx.util.Pair;

@Stateless
@WebService(name="FileShare", serviceName="FileShareService", targetNamespace="http://localhost:8080/WebService")
public class FileShareImplementation implements FileShareService{
	Connection dbConnection;    // The connection to the database
	
	@Resource(mappedName = "java:/MySqlDS")
    private javax.sql.DataSource dataSource;
	
	@Override
	@WebMethod
    public String[][] searchFilesByAddress(String address) {
		return null;
//    	fileInfo[] res = null;
//    	List<fileInfo> tmpArray = new ArrayList<fileInfo>();
//    	Statement query;
//		ResultSet results;
//		boolean rowFound = false;
//    	try {
//    		query = dbConnection.createStatement();
//			results = query.executeQuery("SELECT * from `available_files` WHERE `address`='" + address + "'");
//			rowFound = results.next();	    	
//	    	while(rowFound){
//	    		tmpArray.add(new fileInfo(results.getInt(1),results.getString(2),results.getString(3),results.getString(4), results.getLong(5)));
//	    		rowFound = results.next();
//	    	}
//	    	if(!tmpArray.isEmpty()){
//	    		res = new fileInfo[tmpArray.size()];
//	    		tmpArray.toArray(res);
//	    	}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//    	return res;
	}

	@Override
	@WebMethod
	//Search for the file specified by the filename entered and return a <filename, fid> list
    public String[][] searchFilesByName(String filename){
		return null;
//    	fileInfo[] res = null;
//    	List<fileInfo> tmpArray = new ArrayList<fileInfo>();
//    	Statement query;
//		ResultSet results;
//		boolean rowFound = false;
//    	try {
//    		query = dbConnection.createStatement();
//			results = query.executeQuery("SELECT * from `available_files` WHERE `name` LIKE '" + filename + "'");
//			rowFound = results.next();	    	
//	    	while(rowFound){
//	    		tmpArray.add(new fileInfo(results.getInt(1),results.getString(2),results.getString(3),results.getString(4), results.getLong(5)));
//	    		rowFound = results.next();
//	    	}
//	    	if(!tmpArray.isEmpty()){
//	    		res = new fileInfo[tmpArray.size()];
//	    		tmpArray.toArray(res);
//	    	}
//		} catch (SQLException e) {
//			e.printStackTrace();
//			res = null;
//		}
//    	return res;
    }
    
	@Override
	@WebMethod
    //Search for all files and return a <filename, fid> list
    public List<String[]> getAllSharedFiles(){
    	List<String[]> res = new ArrayList<String[]>();
    	List<String[]> tmpArray = new ArrayList<String[]>();
    	Statement query;
		ResultSet results;
		boolean rowFound = false;
    	try {
    		dbConnection=dataSource.getConnection();
    		query = dbConnection.createStatement();
			results = query.executeQuery("SELECT * from `available_files`");
			rowFound = results.next();	    	
	    	while(rowFound){
//	    		tmpArray.add(new fileInfo(results.getInt(1),results.getString(2),results.getString(3),results.getString(4), results.getLong(5)));
	    		rowFound = results.next();
	    	}
	    	if(!tmpArray.isEmpty()){
//	    		res = new fileInfo[tmpArray.size()];
//	    		tmpArray.toArray(res);
	    	}
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	return res;
    }    
	
	@Override
	@WebMethod
    //Register a file from a client based on
    public void registerFile(String filename, String filepath, String clientAddress, long size) {
		Statement query;
		int results;
		boolean res = false;
		try {
			query = dbConnection.createStatement();
			results = query.executeUpdate("INSERT INTO `available_files` (`fid`, `name`, `path`, `address`, `size`) VALUES (NULL,'" + filename + "','" + filepath + "','" + clientAddress+ "','" + size +"')");
			if(results==1){
				res=true;
				//ResultSet results = query.executeQuery("SELECT MAX(fid) FROM available_files");				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }
	
	@Override
	@WebMethod
    //Used to unregister a file based off an fid
    //Files from each client are all unregistered upon the closing of the client's window
	public boolean unRegisterFile(int fid) {
    Statement query;
	int results;
	boolean res = false;
	try{
	    query = dbConnection.createStatement();
		results = query.executeUpdate("DELETE FROM `available_files` WHERE `fid` ='" + fid + "'");
		if(results==1){
			res = true;
		}
	}catch(Exception e){
		e.printStackTrace();
	}
	return res;
	}
}
