package com.example.microservice.controller;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.microservice.entity.Contact;
import com.example.microservice.entity.User;
import com.example.microservice.service.userService;

import java.awt.Desktop;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;



import javax.imageio.ImageIO;

@RestController
@RequestMapping("/user")
public class userController {

	@Autowired
	private userService userService;

	@Autowired
	private RestTemplate restTemplate;

	@SuppressWarnings("unchecked")
	@GetMapping("/{userId}")
	public User getUser(@PathVariable("userId") Long userId) {
		User user = userService.getUser(userId);
		
		/*HttpHeaders headers = new HttpHeaders();
		headers.add("username", "amritpal");
		headers.add("password", "baath");
		HttpEntity<String> entity = new HttpEntity<String>(headers);
		ResponseEntity<Object> response = this.restTemplate.exchange(uri, HttpMethod.GET, entity, Object.class);*/

		// http://localhost:9002/contact/user/1
	//	ResponseEntity<Object> response = this.restTemplate.getForEntity("http://localhost:9002/contact/user/"+userId,Object.class);
		ResponseEntity<Object> response = this.restTemplate.getForEntity("http://contact-service/contact/user/"+userId,Object.class);
		Object cv = response.getBody();		
		/*
		 * java.util.List<Contact> l1 = new ArrayList<Contact>(); for (int i = 0; i <
		 * cv.length; i++) { l1.add(cv[i]);
		 * 
		 * }
		 * 
		 * user.setContacts(l1);
		 */
		user.setContacts((List<Contact>) cv);
		return user;

	}
	private static final String[] browsers = {"google-chrome", "firefox", "mozilla", "epiphany",
            "konqueror", "netscape", "opera", "links", "lynx", "chromium", "brave-browser"};
	@GetMapping("/gle")
	public void google()
	{
	
		

	        String url="https://www.google.com/";
	        System.out.println("Please open the following address in your browser: ");
	        System.out.println(url);
	        try {
	            if (isMacOperatingSystem()) {
	                openUrlInDefaultMacOsBrowser(url);
	            } else if (isWindowsOperatingSystem()) {
	                openUrlInDefaultWindowsBrowser(url);
	            } else {
	                openUrlInDefaultUnixBrowser(url);
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
		//	return null;
	}
	    

	    private boolean isMacOperatingSystem() {
	        return getOperatingSystemName().startsWith("Mac OS");
	    }

	    private boolean isWindowsOperatingSystem() {
	        return getOperatingSystemName().startsWith("Windows");
	    }

	    private String getOperatingSystemName() {
	        return System.getProperty("os.name");
	    }

	    public void openUrlInDefaultMacOsBrowser(String url) throws IOException {
	        System.out.println("Attempting to open that address in the default browser now...");
	        Runtime.getRuntime().exec(String.format("open %s", url));
	    }

	    private void openUrlInDefaultWindowsBrowser(String url) throws IOException {
	        System.out.println("Attempting to open that address in the default browser now...");
	        Runtime.getRuntime().exec(String.format("rundll32 url.dll,FileProtocolHandler %s", url));
	    }

	    private void openUrlInDefaultUnixBrowser(String url) throws Exception {
	        String browser = null;
	        for (String b : browsers) {
	            if (browser == null && Runtime.getRuntime().exec(new String[]{"which", b}).getInputStream().read() != -1) {
	                System.out.println("Attempting to open that address in the default browser now...");
	                Runtime.getRuntime().exec(new String[]{browser = b, url});
	            }
	        }
	        if (browser == null) {
	            throw new Exception("No web browser found");
	        }
	       
	    
	}
	    
	    
	    @GetMapping("/gle2")
		public void google2()
		{ 
	    	/*Runtime rt = Runtime.getRuntime();
	    	String url = "https://google.com";
	    	try {
	    		rt.exec("rundll32 url.dll,FileProtocolHandler " + url);
	    	} catch (IOException ioException) {
	    		ioException.printStackTrace();
	    	}
			return null;*/
	    	
	    	String url = "http://www.google.com";
	    	String os = System.getProperty("os.name").toLowerCase();
	    	System.out.println(os);
	            Runtime rt = Runtime.getRuntime();
	    	
	    	try{

	    	    if (os.indexOf( "win" ) >= 0) {

	    	        // this doesn't support showing urls in the form of "page.html#nameLink" 
	    	        rt.exec( "rundll32 url.dll,FileProtocolHandler " + url);

	    	    } else if (os.indexOf( "mac" ) >= 0) {

	    	        rt.exec( "open " + url);

	                } else if (os.indexOf( "nix") >=0 || os.indexOf( "nux") >=0) {

	    	        // Do a best guess on unix until we get a platform independent way
	    	        // Build a list of browsers to try, in this order.
	    	        String[] browsers = {"epiphany", "firefox", "mozilla", "konqueror",
	    	       			             "netscape","opera","links","lynx"};
	    	        	
	    	        // Build a command string which looks like "browser1 "url" || browser2 "url" ||..."
	    	        StringBuffer cmd = new StringBuffer();
	    	        for (int i=0; i<browsers.length; i++)
	    	            cmd.append( (i==0  ? "" : " || " ) + browsers[i] +" \"" + url + "\" ");
	    	        	
	    	        rt.exec(new String[] { "sh", "-c", cmd.toString() });

	               } else {
	                  //  return null;
	               }
	           }catch (Exception e){
	    	  //  return null;
	           }
		//	return null;
		}
	
	    
	    @GetMapping("/download")
	    public void download() throws IOException
	    {
        File file = new File("D:\\zip folder\\newfolder2");
        boolean bool=false;
        if (!file.exists())
        {
            System.out.println("Directory created successfully");
          bool = file.mkdir();
        }
         if(bool){
             System.out.println("Directory created successfully");
          }else{
             System.out.println("Sorry couldnt create specified directory");
          }
        
          
//          String home = System.getProperty("user.home");
//          System.out.println(home);
//          File file2 = new File(home+"/Downloads/" + "newfolder3");
//          file2.mkdir();
        //  File file3 = new File("D:\\zip folder\\newfolder2"+"/abc.txt");
          String path="D:\\zip folder\\newfolder2\\";
      //    file3.createNewFile();
         FileOutputStream fos = new FileOutputStream(  path + "" + "abc" + ".txt");
         
         
            Path dirPath= Paths.get("D:\\zip folder\\newfolder2\\");
            System.out.println("Folder already deleted"+dirPath.toString());
            String zipFilePathStr = dirPath.toString() + ".zip";
            
            File file5 = new File(zipFilePathStr);
            if(file5.exists()) {
                file5.delete();
                System.out.println("Folder already deleted");
            }
            
            Path zipFilePath = Files.createFile(Paths.get(zipFilePathStr));
            



           try (ZipOutputStream zs = new ZipOutputStream(Files.newOutputStream(zipFilePath))) {
                Files.list(dirPath)
                    .filter(filePath-> !Files.isDirectory(filePath))
                    .forEach(filePath-> {
                        ZipEntry zipEntry = new ZipEntry(dirPath.relativize(filePath).toString());
                        try {
                            zs.putNextEntry(zipEntry);
                            Files.copy(filePath, zs);
                            zs.closeEntry();
                        }
                        catch (IOException e) {
                            System.err.println(e);
                        }
                    });
            }



        

    }




	    
	

}
