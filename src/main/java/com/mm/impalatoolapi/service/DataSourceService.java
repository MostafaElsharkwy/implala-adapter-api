package com.mm.impalatoolapi.service;

import java.io.StringWriter;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
 
import org.springframework.stereotype.Component;

//https://stackoverflow.com/questions/6514876/most-efficient-conversion-of-resultset-to-json
 
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.node.ObjectNode;

@Component("DataSourceService")
public class DataSourceService {

	Logger logger = LoggerFactory.getLogger(DataSourceService.class);

	@Autowired
	public DataSource dataSource;

 

	/**
	 * 
	 * @param query
	 *            - query1
	 * @return List of Result in Map 
	 */
	public List<Map> excuteQuery(String query) {
		if (query == null) {
			return null;
		}

		SimpleModule module = new SimpleModule();
		module.addSerializer(new ResultSetSerializer());

		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.registerModule(module);
		
		List<Map> mapList = new ArrayList<>();
		try {
			logger.error("Query: " + query);
			Connection connection = dataSource.getConnection();
			PreparedStatement ps = connection.prepareStatement(query); 
			//ps.setString(1, "%" + query + "%");
			//ps.setInt(2, Integer.parseInt(querylimit));
			ResultSet resultset = ps.executeQuery();
			ResultSetMetaData rsmd = resultset.getMetaData();
            int numColumns = rsmd.getColumnCount();
            String[] columnNames = new String[numColumns];
            int[] columnTypes = new int[numColumns];

            for (int i = 0; i < columnNames.length; i++) {
                columnNames[i] = rsmd.getColumnLabel(i + 1);
                columnTypes[i] = rsmd.getColumnType(i + 1);
            }

        
            Map rowMap;
            
            while (resultset.next()) {

            	 boolean b;
                 long l;
                 double d;
                 
            	  rowMap = new HashMap<String,String>();
            	  mapList.add(rowMap);
                for (int i = 0; i < columnNames.length; i++) {
                	// rowMap.put(columnNames[i], rowMap);
                	
                	  switch (columnTypes[i]) {

                      case Types.INTEGER:
                          l = resultset.getInt(i + 1);
                          if (resultset.wasNull()) {
                        	  rowMap.put(columnNames[i], null);
                          } else {
                        	  rowMap.put(columnNames[i], l);
                          }
                          break;

                      case Types.BIGINT:
                          l = resultset.getLong(i + 1);
                          if (resultset.wasNull()) {
                        	  rowMap.put(columnNames[i], null);
                          } else {
                        	  rowMap.put(columnNames[i], l);
                          }
                          break;

                      case Types.DECIMAL:
                      case Types.NUMERIC:
                          
                          rowMap.put(columnNames[i], resultset.getBigDecimal(i + 1));
                          break;

                      case Types.FLOAT:
                      case Types.REAL:
                      case Types.DOUBLE:
                          d = resultset.getDouble(i + 1);
                          if (resultset.wasNull()) {
                        	  rowMap.put(columnNames[i], null);
                          } else {
                        	  rowMap.put(columnNames[i], d);
                          }
                          break;

                      case Types.NVARCHAR:
                      case Types.VARCHAR:
                      case Types.LONGNVARCHAR:
                      case Types.LONGVARCHAR:
                       
                          rowMap.put(columnNames[i], resultset.getString(i + 1));
                          break;

                      case Types.BOOLEAN:
                      case Types.BIT:
                          b = resultset.getBoolean(i + 1);
                          if (resultset.wasNull()) {
                        	  rowMap.put(columnNames[i], null);
                          } else {
                              
                              rowMap.put(columnNames[i], b);
                          }
                          break;

                      case Types.BINARY:
                      case Types.VARBINARY:
                      case Types.LONGVARBINARY:
                    	  rowMap.put(columnNames[i],resultset.getBytes(i + 1));
                          break;

                      case Types.TINYINT:
                      case Types.SMALLINT:
                          l = resultset.getShort(i + 1);
                          if (resultset.wasNull()) {
                        	  rowMap.put(columnNames[i], null);
                          } else {
                        	  rowMap.put(columnNames[i], l);
                          }
                          break;

                      case Types.DATE:
                          //provider.defaultSerializeDateValue(rs.getDate(i + 1), jgen);
                    	  rowMap.put(columnNames[i],resultset.getDate(i + 1));
                          break;

                      case Types.TIMESTAMP:
                          //provider.defaultSerializeDateValue(rs.getTime(i + 1), jgen);
                    	  rowMap.put(columnNames[i],resultset.getTime(i + 1));
                          break;

                      case Types.BLOB:
                          Blob blob = resultset.getBlob(i);
                          //provider.defaultSerializeValue(blob.getBinaryStream(), jgen);
                          rowMap.put(columnNames[i],blob.getBinaryStream());
                          blob.free();
                          break;

                      case Types.CLOB:
                          Clob clob = resultset.getClob(i);
                          //provider.defaultSerializeValue(clob.getCharacterStream(), jgen);
                          rowMap.put(columnNames[i],clob.getCharacterStream());
                          clob.free();
                          break;

                      case Types.ARRAY:
                          throw new RuntimeException("ResultSetSerializer not yet implemented for SQL type ARRAY");

                      case Types.STRUCT:
                          throw new RuntimeException("ResultSetSerializer not yet implemented for SQL type STRUCT");

                      case Types.DISTINCT:
                          throw new RuntimeException("ResultSetSerializer not yet implemented for SQL type DISTINCT");

                      case Types.REF:
                          throw new RuntimeException("ResultSetSerializer not yet implemented for SQL type REF");

//                      case Types.JAVA_OBJECT:
                      default:
                           logger.error("not handled Type ");
                          break;
                      }
                	  
                }
                }
 
			resultset.close();
			ps.close();
			connection.close();
			resultset = null;
			ps = null;
			connection = null;
			 

			//logger.error("Size=" + resultset.l.size());
		} catch (Exception e) {
			logger.error("Error in search", e);
		}

		return mapList;
	}
	/**
	 * 
	 * @param query
	 *            - search msg
	 * @return List of Twitter2
	 */
	public List<Object> excuteQueryObj(String query) {
		if (query == null) {
			return null;
		}

		SimpleModule module = new SimpleModule();
		module.addSerializer(new ResultSetSerializer());

		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.registerModule(module);
		
		List<Object> tweets = new ArrayList<>();
		try {
			logger.error("Query: " + query);
			Connection connection = dataSource.getConnection();
			PreparedStatement ps = connection.prepareStatement(query); 
			//ps.setString(1, "%" + query + "%");
			//ps.setInt(2, Integer.parseInt(querylimit));
			ResultSet resultset = ps.executeQuery();
//			Twitter2 tweet = null;
			// Use the DataBind Api here
			ObjectNode objectNode = objectMapper.createObjectNode();

			// put the resultset in a containing structure
			objectNode.putPOJO("results", resultset);

			 StringWriter writer = new StringWriter();
			// generate all
			objectMapper.writeValue(writer, objectNode);
			logger.info( writer.toString());
			
 
			resultset.close();
			ps.close();
			connection.close();
			resultset = null;
			ps = null;
			connection = null;
			 

			//logger.error("Size=" + resultset.l.size());
		} catch (Exception e) {
			logger.error("Error in search", e);
		}

		return tweets;
	}
}
