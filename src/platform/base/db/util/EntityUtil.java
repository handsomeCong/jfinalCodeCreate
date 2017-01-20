package platform.base.db.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Date;

import platform.base.db.model.ConnectParamBean;
import platform.base.util.PlatformTool;

public class EntityUtil {
	 private String tablename = "";
	 private String filePath = "";
	 public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getTablename() {
		return tablename;
	}

   public static void createEntity(String path,String lower,String packagePath,String className,String tableName,ConnectParamBean param){
	   new EntityUtil(path,lower,packagePath,className,tableName,param);
   }
	 
	public void setTablename(String tablename) {
		this.tablename = tablename;
	}

	private String[] colnames;
	 private String[] colTypes; 
	 private int[] colSizes; 
	 private boolean f_util = false; 
	 private boolean f_sql = false; 
	    
	  
 
	 public EntityUtil(String path,String lower,String packagePath,String className,String tableName,ConnectParamBean param) {
	     Connection con = null;
	     this.setTablename(tableName);
	     String sql = "select * from " + tablename;
	   PreparedStatement pStemt = null;
	   con =DBConnection.getConnection(param);
	   try{
	   pStemt = con.prepareStatement(sql);
	   ResultSetMetaData rsmd = pStemt.getMetaData();
	   int size = rsmd.getColumnCount();  
	   colnames = new String[size];
	   colTypes = new String[size];
	   colSizes = new int[size];
	   for (int i = 0; i < size; i++) {
	    colnames[i] = rsmd.getColumnName(i + 1);
	    colTypes[i] = rsmd.getColumnTypeName(i + 1);
	    
	    if(colTypes[i].equalsIgnoreCase("datetime")||colTypes[i].equalsIgnoreCase("date")||colTypes[i].equalsIgnoreCase("timestamp")){
	     f_util = true;
	    }
	    if(colTypes[i].equalsIgnoreCase("image") || colTypes[i].equalsIgnoreCase("text")){
	     f_sql = true;
	    }
	    colSizes[i] = rsmd.getColumnDisplaySize(i + 1);
	   }
	   }catch (Exception e) {
		// TODO: handle exception
	}
	   String content = parse(packagePath, lower,className,colnames,colTypes,colSizes);
	   
	   try {
	    System.out.println("文件地址："+ className + "Form.java");
	    FileWriter fw = new FileWriter(path);
	    PrintWriter pw = new PrintWriter(fw);
	    System.out.println(content);
	    pw.println(content);
	    pw.flush();
	    pw.close();
	   } catch (IOException e) {
	    e.printStackTrace();
	   }
	  } 
	  
 
	 private String parse(String packagePath,String lower,String className,String[] colnames, String[] colTypes, int[] colSizes) {
	  StringBuffer sb = new StringBuffer();
	  sb.append("package "+packagePath+".web."+lower+".form;\r\n");
	  if(f_util){
	   sb.append("import java.util.Date;\r\n");
	  }
	  if(f_sql){
	   sb.append("import java.sql.*;\r\n");
	  }
	  sb.append("import com.v8.base.form.BaseForm;\r\n");
	  sb.append("\r\n");
	  sb.append("   /**\r\n");
	  sb.append("    * "+tablename+" model\r\n");
	  sb.append("    * "+new Date()+"  \r\n");
	  sb.append("    */ \r\n");
	  sb.append("\r\n\r\npublic class " + className + "Form extends BaseForm  {\r\n");
	  processAllAttrs(sb);//
	  processAllMethod(sb);
	  
	  String sbString = "";
	try {
		sbString = new String(sb.toString().getBytes("ISO-8859-1"), "GBK");
	} catch (UnsupportedEncodingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	  return sbString;
	 }
	 
 
	 private void processAllAttrs(StringBuffer sb) {
	
	  for (int i = 0; i < colnames.length; i++) {
	   String column=PlatformTool.toFirstLowerString(PlatformTool.conversionObject(colnames[i]));
	   sb.append("\tprivate " + sqlType2JavaType(colTypes[i]) + " " +  column+ ";\r\n");
	  }
	  
	 }
 
	 private void processAllMethod(StringBuffer sb) {
	  
	  for (int i = 0; i < colnames.length; i++) {
		 String column=PlatformTool.toFirstLowerString(PlatformTool.conversionObject(colnames[i]));
	   sb.append("\tpublic void set" + initcap(column) + "(" + sqlType2JavaType(colTypes[i]) + " " + 
			   column + "){\r\n");
	   sb.append("\tthis." + column+ "=" + column + ";\r\n");
	   sb.append("\t}\r\n");
	   sb.append("\tpublic " + sqlType2JavaType(colTypes[i]) + " get" + initcap(column) + "(){\r\n");
	   sb.append("\t\treturn " + column+ ";\r\n");
	   sb.append("\t}\r\n");
	  }
 
//	  sb.append("   /**\r\n");
//	  sb.append("    * "+tablename+" id model\r\n");
//	  sb.append("    *  "+initcap(tablename)+"(String id)  \r\n");
//	  sb.append("    */ \r\n");
//	  sb.append("\tpublic "+initcap(tablename)+"(){\r\n");
//	  sb.append("\t}\r\n");
//	  sb.append("\n");
//	  sb.append("\tpublic "+initcap(tablename)+"(String id){\r\n");
//	  sb.append("\tConnection conn = null;\r\n");
//	  sb.append("\tString sql = \"select * from "+tablename+" where id='\" + id + \"'\";\r\n");
//	  sb.append("\ttry {\r\n");
//	  sb.append("\tconn = DBConnection.getConnection();\r\n");
//	  sb.append("\tStatement stsm = conn.createStatement();\r\n");
//	  sb.append("\tResultSet rs = stsm.executeQuery(sql);\r\n");
//	  sb.append("\twhile (rs.next()) {\r\n");
//	  for (int i = 0; i < colnames.length; i++) {
//	  sb.append("\tthis."+colnames[i]+"=rs.get"+initcap(sqlType2JavaType(colTypes[i]))+"(\""+colnames[i]+"\");\r\n");
//				 
//	  }
//	  sb.append("\t}\r\n");
//	  sb.append("\tDBConnection.closeAll(conn, stsm, rs);\r\n");
//	  sb.append("\t} catch (SQLException e) {\r\n");
//	  sb.append("\t}\r\n");
//	  sb.append("\t}\r\n");
//	  sb.append("\n");
// 
//	  
//	  
//	  sb.append("\t public int add(){\r\n ");
//	  sb.append("\tint result=1;\r\n");
//	  sb.append("\tString sql=\" insert into "+tablename+" (");
//	  for (int i = 0; i < colnames.length; i++) {
//		  if(i!=colnames.length-1){
//			  sb.append(colnames[i]+",");
//		  }else{
//			  sb.append(colnames[i]);
//		  }
//	  }
//	  sb.append(") ");
//	  sb.append(" values (\"\r\n");
//	  for (int i = 0; i < colnames.length; i++) {
//		  if(sqlType2JavaType(colTypes[i]).equalsIgnoreCase("byte")||
//		     sqlType2JavaType(colTypes[i]).equalsIgnoreCase("int")||
//		     sqlType2JavaType(colTypes[i]).equalsIgnoreCase("long")||
//		     sqlType2JavaType(colTypes[i]).equalsIgnoreCase("float")||
//		     sqlType2JavaType(colTypes[i]).equalsIgnoreCase("double")){
//			  if(i!=colnames.length-1){
//				  sb.append("\t+\"\"+this."+colnames[i]+"+\",\"\r\n");
//			  }else{
//				  sb.append("\t+\"\"+this."+colnames[i]+"+\"\"\r\n");
//			  }
//		  }else{
//			  if(i!=colnames.length-1){
//				  sb.append("\t+\"'\"+this."+colnames[i]+"+\"',\"\r\n");
//			  }else{
//				  sb.append("\t+\"'\"+this."+colnames[i]+"+\"'\"\r\n");
//			  }
//		  }
//	  }
//	  sb.append("\t+\")\";\r\n");
//	  sb.append("\t result = tools.Query.updateField(sql);\r\n");
//	  sb.append("\treturn result;\r\n");
//	  sb.append("\t}\r\n");
//	  sb.append("\n");
//	  
//	  /**
//	   * 鐢熸垚閫氱敤edit鏂规硶
//	   */
//	  sb.append("\t public int edit(){\r\n ");
//	  sb.append("\tString sql = \"update "+tablename+" set \"+");
//	  for (int i = 0; i < colnames.length; i++) {
//	   if(i!=colnames.length-1){
//	  sb.append("\t\" "+colnames[i]+" ='\"+this."+colnames[i]+"+\"',\"+\r\n");
//	   }else{
//		   sb.append("\t\" "+colnames[i]+" ='\"+this."+colnames[i]+"+\"'\"+\r\n");
//	   }
//	  }
//	  sb.append("\t\" where id ='\"+this.id+\"'\";\r\n");
//	  sb.append("\tint result = tools.Query.updateField(sql);\r\n");
//	  sb.append("\treturn result;\r\n");
//	  sb.append("\t}\r\n");
//	  sb.append("\n");
//	  /**
//	   * 鍒犻櫎
//	   */
//	  
//	  sb.append("\tpublic int deleteById() {\r\n");
//	  sb.append("\tString sql = \"delete from "+tablename+" where id = '\" + this.id + \"'\";\r\n");
//	  sb.append("\tint result = Query.updateField(sql);\r\n");
//	  sb.append("\treturn result;\r\n");	
//	  sb.append("\t}\r\n");		 	
//	  sb.append("\n");
//	  /**
//	   * 鐢熸垚query by condition 杩斿洖Arraylist
//	   */
//	  sb.append("\tpublic ArrayList<"+initcap(tablename)+"> queryByCondition(String condition,String limit) {\r\n");
//	  sb.append("\tArrayList<"+initcap(tablename)+"> list = new ArrayList<"+initcap(tablename)+">();\r\n");
//	  sb.append("\tConnection conn = null;\r\n");
//	  sb.append("\tString sql = \"select * from "+tablename+" where 1=1 \" + condition+limit;\r\n");
//	  sb.append("\ttry {\r\n");
//	  sb.append("\tconn = DBConnection.getConnection();\r\n");
//	  sb.append("\tStatement stsm = conn.createStatement();\r\n");
//	  sb.append("\tResultSet rs = stsm.executeQuery(sql);\r\n");
//	  sb.append("\twhile (rs.next()) {\r\n");
//	  sb.append("\t"+initcap(tablename)+" "+tablename+"=new "+initcap(tablename)+"();\r\n");
//	  for (int i = 0; i < colnames.length; i++) {
//		  sb.append("\t"+tablename+"."+colnames[i]+"=rs.get"+initcap(sqlType2JavaType(colTypes[i]))+"(\""+colnames[i]+"\");\r\n");
//		  }
//	  sb.append("\tlist.add("+tablename+");\r\n");
//	  sb.append("\t}\r\n");
//	  sb.append("\tDBConnection.closeAll(conn, stsm, rs);\r\n");
//	  sb.append("\t} catch (SQLException e) {\r\n");
//	  sb.append("\t}\r\n");
//	  sb.append("\treturn list;\r\n");
	  
	  sb.append("\t}\r\n");
	  sb.append("\n");
	 }
	 
	  
	 private String initcap(String str) {
	  
	  char[] ch = str.toCharArray();
	  if(ch[0] >= 'a' && ch[0] <= 'z'){
	   ch[0] = (char)(ch[0] - 32);
	  }
	  
	  return new String(ch);
	 }

	 
	 private String sqlType2JavaType(String sqlType) {
	  
	  if(sqlType.equalsIgnoreCase("bit")){
	   return "boolean";
	  }else if(sqlType.equalsIgnoreCase("tinyint")){
	   return "byte";
	  }else if(sqlType.equalsIgnoreCase("smallint")){
	   return "short";
	  }else if(sqlType.equalsIgnoreCase("int")||sqlType.equalsIgnoreCase("Integer")){
	   return "int";
	  }else if(sqlType.equalsIgnoreCase("bigint")){
	   return "long";
	  }else if(sqlType.equalsIgnoreCase("float")){
	   return "float";
	  }else if(sqlType.equalsIgnoreCase("decimal") || sqlType.equalsIgnoreCase("numeric") 
	    || sqlType.equalsIgnoreCase("real") || sqlType.equalsIgnoreCase("money") 
	    || sqlType.equalsIgnoreCase("smallmoney")||sqlType.equalsIgnoreCase("double")){
	   return "BigDecimal";
	  }else if(sqlType.equalsIgnoreCase("varchar") || sqlType.equalsIgnoreCase("char") 
	    || sqlType.equalsIgnoreCase("nvarchar") || sqlType.equalsIgnoreCase("nchar") 
	    || sqlType.equalsIgnoreCase("text")||sqlType.equalsIgnoreCase("datetime")||sqlType.equalsIgnoreCase("time")||sqlType.equalsIgnoreCase("timestamp")){
	   return "String";
	   
	  }else if(sqlType.equalsIgnoreCase("image")){
	   return "Blod";
	  }
	  
	  return null;
	 }
	 
	 /**
	  * 
	  * TODO
	  * @param args
	  */
	 public static void main(String[] args) {
		 	//	EntityUtil.createEntity("model","zj_bbs_topic",null);
	 }

	}