package platform.codecreate.generate;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.velocity.app.Velocity;

import platform.base.util.PlatformTool;

import com.jfinal.render.Render;

public class GenerateEclipseRender extends Render {
    private transient static final String encoding = getEncoding();
    private transient static final String contentType = "text/html;charset=" + encoding;
    private transient static final Properties properties = new Properties();
    private transient static boolean notInit = true;
    String outFilePath = "D:/ProjectDemo";

    private Map<String, Object> map;

    public GenerateEclipseRender(Map<String, Object> map) {
        this.map = map;
    }

    @Override
    public void render() {
        if (notInit) {
            String webPath = request.getRealPath("/");
            properties.setProperty(Velocity.FILE_RESOURCE_LOADER_PATH, webPath);
            properties.setProperty(Velocity.ENCODING_DEFAULT, encoding);
            properties.setProperty(Velocity.INPUT_ENCODING, encoding);
            properties.setProperty(Velocity.OUTPUT_ENCODING, encoding);
            Velocity.init(properties);
            notInit = false;
        }
        Generator generator = new Generator();
        String filedir =PlatformTool.getNowDateStr();
        generator.generaterEclipse(map, filedir, outFilePath, (String) request.getAttribute("contextpath"));
        PlatformTool.compressToZipFile(outFilePath + "\\" + filedir, outFilePath + "\\" + filedir + ".zip");
        PlatformTool.deleteDir(outFilePath + "\\" + filedir);
        File file = new File(outFilePath + "\\" + filedir + ".zip");
        if (file.exists()) {
            BufferedOutputStream out = null;
            FileInputStream fis = null;
            try {
                fis = new FileInputStream(file);
                out = new BufferedOutputStream(response.getOutputStream());
                response.setHeader("Pragma", "No-cache");
                response.setHeader("Cache-Control", "no-cache");
                response.setDateHeader("Expires", 0);
                response.setContentType("application/octet-stream;charset=UTF-8");
                response.addHeader("Content-Disposition", "filename=" + URLEncoder.encode(filedir + ".zip", "UTF-8"));
                IOUtils.copy(fis, out);
                response.setStatus(HttpServletResponse.SC_OK);
                response.flushBuffer();
            } catch (Exception e) {
                throw new RuntimeException(e);
            } finally {
                if (out != null) {
                    try {
                        out.close();
                        fis.close();
                        out = null;
                        fis = null;
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

}
