package platform.base.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

public class PlatformTool {

    public static String generateUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    /**
     * 把表名转换成对象名
     * 
     * @param talbeName
     * @return
     */
    public static String conversionObject(String talbeName) {
        StringBuilder sb = new StringBuilder();
        String[] ddd = talbeName.split("_");
        for (String ss : ddd) {
            sb.append(ss.substring(0, 1).toUpperCase());
            sb.append(ss.substring(1, ss.length()).toLowerCase());
        }
        return sb.toString();
    }

    public static String toFirstLowerString(String src) {
        StringBuilder sb = new StringBuilder();
        sb.append(src.substring(0, 1).toLowerCase());
        sb.append(src.substring(1, src.length()));
        return sb.toString();
    }

    public static String conversionObjectAttribute(String fieldName) {
        return toFirstLowerString(conversionObject(fieldName));
    }

    public static void createDirPath(String dirPath) {
        File dir = new File(dirPath);
        if (!dir.exists()) {
            dir.mkdirs();
        }
    }

    public static void createFile(String filePath) {
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (Exception e) {
            System.out.println("创建文件失败:" + e.toString());
        }
    }

    public static void generateFile(String templateFileName, Map ctx, String filePath) {
        createFile(filePath);
        try {
            OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(filePath), "UTF-8");
            // 初始化上下文
            VelocityContext context = new VelocityContext();
            Iterator iterator = ctx.keySet().iterator();
            while (iterator.hasNext()) {
                Object next = iterator.next();
                Object object = ctx.get(next);
                // 添加数据到上下文中
                context.put(String.valueOf(next), object);
            }
            // 初始化vm模板
            Template template = Velocity.getTemplate(templateFileName);
            template.merge(context, writer);
            writer.flush();
            writer.close();
            writer = null;
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void deleteDir(String dirPath) {
        delFolder(dirPath);
    }

    public static void delFolder(String folderPath) {
        try {
            delAllFile(folderPath); // 删除完里面所有内容
            String filePath = folderPath;
            filePath = filePath.toString();
            java.io.File myFilePath = new java.io.File(filePath);
            myFilePath.delete(); // 删除空文件夹
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean delAllFile(String path) {
        boolean flag = false;
        File file = new File(path);
        if (!file.exists()) {
            return flag;
        }
        if (!file.isDirectory()) {
            return flag;
        }
        String[] tempList = file.list();
        File temp = null;
        for (int i = 0; i < tempList.length; i++) {
            if (path.endsWith(File.separator)) {
                temp = new File(path + tempList[i]);
            } else {
                temp = new File(path + File.separator + tempList[i]);
            }
            if (temp.isFile()) {
                temp.delete();
            }
            if (temp.isDirectory()) {
                delAllFile(path + "/" + tempList[i]);// 先删除文件夹里面的文件
                delFolder(path + "/" + tempList[i]);// 再删除空文件夹
                flag = true;
            }
        }
        return flag;
    }

    /**
     * zip压缩功能. 压缩baseDir(文件夹目录)下所有文件，包括子目录
     * 
     * @param baseDir
     * @param fileName
     */
    public static void compressToZipFile(String baseDir, String fileName) {
        List fileList = getSubFiles(new File(baseDir));
        ZipOutputStream zos;
        try {
            zos = new ZipOutputStream(new FileOutputStream(fileName));
            ZipEntry ze = null;
            byte[] buf = new byte[1024];
            int readLen = 0;
            for (int i = 0; i < fileList.size(); i++) {
                File f = (File) fileList.get(i);
                ze = new ZipEntry(getAbsFileName(baseDir, f));
                ze.setSize(f.length());
                ze.setTime(f.lastModified());
                zos.putNextEntry(ze);
                InputStream is = new BufferedInputStream(new FileInputStream(f));
                while ((readLen = is.read(buf, 0, 1024)) != -1) {
                    zos.write(buf, 0, readLen);
                }
                is.close();
            }
            zos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 取得指定目录下的所有文件列表，包括子目录.
     * 
     * @param baseDir File 指定的目录
     * @return 包含java.io.File的List
     */
    private static List getSubFiles(File baseDir) {
        List ret = new ArrayList();
        File[] tmp = baseDir.listFiles();
        for (int i = 0; i < tmp.length; i++) {
            // if (!isZipFile(tmp[i].getAbsolutePath())) {
            if (tmp[i].isFile())
                ret.add(tmp[i]);
            if (tmp[i].isDirectory())
                ret.addAll(getSubFiles(tmp[i]));
            // }
        }
        return ret;
    }

    private static boolean isZipFile(String filename) {
        return filename.indexOf("settings") > 0 || filename.indexOf("build") > 0 || filename.indexOf(".classpath") > 0
                || filename.indexOf(".project") > 0 || filename.indexOf("build.properties") > 0
                || filename.indexOf("build.xml") > 0 || filename.indexOf("src\\test") > 0
                || filename.indexOf("datasource.xml") > 0 || filename.indexOf("ehcache.xml") > 0
                || filename.indexOf("service.properties") > 0 || filename.indexOf("release_datasource.xml") > 0
                || filename.indexOf("project.properties") > 0 || filename.indexOf("log4j.properties") > 0
                || filename.indexOf("jdbc.properties") > 0 || filename.indexOf("boxview") > 0
                || filename.indexOf("META-INF") > 0 || filename.indexOf("WebContent\\index.jsp") > 0
                || filename.indexOf("WebContent\\Print.htm") > 0 || filename.indexOf("WebContent\\swfupload.swf") > 0
                || filename.indexOf("WebContent\\workMenu.xml") > 0 || filename.indexOf("WEB-INF\\lib") > 0
                || filename.indexOf("WEB-INF\\tld") > 0 || filename.indexOf("WEB-INF\\boxmvc-servlet.xml") > 0
                || filename.indexOf("WEB-INF\\geronimo-web.xml") > 0 || filename.indexOf("WEB-INF\\web.xml") > 0
                || filename.indexOf("WEB-INF\\jsp\\deleteSuccess.jsp") > 0
                || filename.indexOf("WEB-INF\\jsp\\error.jsp") > 0 || filename.indexOf("WEB-INF\\jsp\\index.jsp") > 0
                || filename.indexOf("WEB-INF\\index.jsp") > 0;
    }

    /**
     * 给定根目录，返回另一个文件名的相对路径，用于zip文件中的路径.
     * 
     * @param baseDir java.lang.String 根目录
     * @param realFileName java.io.File 实际的文件名
     * @return 相对文件名
     */

    private static String getAbsFileName(String baseDir, File realFileName) {
        File real = realFileName;
        File base = new File(baseDir);
        String ret = real.getName();
        while (true) {
            real = real.getParentFile();
            if (real == null)
                break;
            if (real.equals(base))
                break;
            else
                ret = real.getName() + "/" + ret;
        }
        return ret;
    }

    /**
     * 给定根目录，返回一个相对路径所对应的实际文件名.
     * 
     * @param baseDir 指定根目录
     * @param absFileName 相对路径名，来自于ZipEntry中的name
     * @return java.io.File 实际的文件
     */
    public static File getRealFileName(String baseDir, String absFileName) {
        String[] dirs = absFileName.split("/");
        File ret = new File(baseDir);
        if (dirs.length >= 1) {
            for (int i = 0; i < dirs.length - 1; i++) {
                ret = new File(ret, dirs[i]);
            }
            if (!ret.exists())
                ret.mkdirs();
            ret = new File(ret, dirs[dirs.length - 1]);
            return ret;

        }
        return ret;
    }

    public static String getNowDateStr() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        return sdf.format(date);
    }

    public static String getCtime() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(date);
    }

    public static void main(String[] args) {
        System.out.println(conversionObjectAttribute("user_name_secruity"));
    }
}
