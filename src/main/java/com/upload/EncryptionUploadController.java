package com.upload;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import javax.sql.DataSource;

import org.apache.tomcat.util.codec.binary.Base64;
import org.json.JSONObject;

import com.authentication.User;
import com.dashboard.FileDbUtil;
import com.dashboard.Filex;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;

import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Base64.Encoder;
import java.util.Date;
import java.util.Random;

import javax.annotation.Resource;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;  
import javax.crypto.CipherInputStream;
import javax.crypto.IllegalBlockSizeException;

/**
 * Servlet implementation class EncryptionUploadController
 */
@WebServlet(name = "/EncryptionUploadController",asyncSupported = true,  urlPatterns = {"/upload_encrypted"})
@MultipartConfig(
		  fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
		  maxFileSize = 1024 * 1024 * 5,      // 5 MB
		  maxRequestSize = 1024 * 1024 * 100   // 100 MB
		)	
public class EncryptionUploadController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private FileDbUtil fileDbUtil;
	
	// Define datasource and connection pool for Resource Injection
	@Resource(name="jdbc/dms_db")
	private DataSource dataSource;
	
	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		
		// create folder db util ... and pass in the conn pool / datasource
		try {
			fileDbUtil = new FileDbUtil(dataSource);
		}
		catch(Exception exc){
			throw new ServletException(exc);
		}
	}
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EncryptionUploadController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//Step 2: get the printwriter
				PrintWriter out = response.getWriter();
				
				String filename = request.getParameter("filename");
				
				String fileId = request.getParameter("file_id").trim();
				
				// Access session
				HttpSession session = request.getSession(true);
				
				int userId = (int) session.getAttribute("id");
				
				int fileIdInt = Integer.parseInt(request.getParameter("file_id").trim());

				//get file path string
				String encryptedFilePathString = getServletContext().getRealPath("/"+"aes_encrypted_file_upload" +File.separator + filename);
				
				//get file path string
				String decryptedFilePathString = getServletContext().getRealPath("/"+"aes_decrypted_file_download" +File.separator + filename);
				
				//file to be downloaded
				File downloadedFile = new File(decryptedFilePathString);
				File encryptedFile = new File(encryptedFilePathString);
				
				//get file type
				String fileType = Files.probeContentType(encryptedFile.toPath().toAbsolutePath());
				
				JSONObject json = new JSONObject();
				if(encryptedFile.exists()) {
					
					// create new folder object here;
					Filex theFile = new Filex(fileIdInt, userId);
					
					// get the user to the database
					try {
						
						
						Filex fileData = fileDbUtil.getFile(theFile);
						
						//Decryption starts here
						decryptAES(fileData.getEncryptedAesKey(), fileData.getEncryptedPrivateKey(), fileData.getEncryptedPublicKey(), encryptedFilePathString, decryptedFilePathString);
						
						//System.out.println("encryptedFilePathString: "+encryptedFilePathString);
						//System.out.println("decryptedFilePathString: "+decryptedFilePathString);
						//System.out.println("filename: "+filename);
						//System.out.println("fileId: "+fileId);
						
						
						response.setContentType(fileType);
						response.setContentLength((int) downloadedFile.length());
						
						//force to download
						String headerKey = "Content-Disposition";
						String headerValue = String.format("attachment: filename=\"%s\"", downloadedFile.getName());
						response.setHeader(headerKey, headerValue);
						
						FileInputStream fileInputStream = new FileInputStream(downloadedFile);
						//ServletOutputStream  outputStream = response.getOutputStream();
						
						int bytez;
						
						while((bytez = fileInputStream.read()) != -1) {
							out.write(bytez);
						}
						fileInputStream.close();
						downloadedFile.delete();
						out.close();
						
						
					} catch (SQLException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException | NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeySpecException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
								
				}else {
					
					
				}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		//JSON Object 
		JSONObject json = new JSONObject();
		
		//Step 1: set the content type
		response.setContentType("text/html");
		
		//Step 2: get the printwriter
		PrintWriter out = response.getWriter();
					
		// Create path components to save the file
		Part filePart = request.getPart("uploaded_file");
		
		//fileName will be the displayname
		String fileName = filePart.getSubmittedFileName();
		
		//fileName for database
		String fileNameDb = fileName.substring(0,  fileName.lastIndexOf("."));
		
		// Access session
		HttpSession session = request.getSession(true);
		
		//get user id
		int userId = (int) session.getAttribute("id");
		
		//get folder id
		int folder_id = Integer.parseInt(request.getParameter("folder_id")); 
		
		//generate random characters
		String randomChars = generateRandomChars(
	            "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890", 17);
		
		//get file extension
		String fileExtension  = fileName.substring(fileName.lastIndexOf("."));
		
		//get file new name
		String fileNewName  = randomChars+fileExtension;
		
		//get file path string
		String filePathString = getServletContext().getRealPath("/"+"file_upload" +File.separator + fileNewName);
		
		//get file path string
		String encryptedFilePathString = getServletContext().getRealPath("/"+"aes_encrypted_file_upload" +File.separator + fileNewName);	
		
		//get file path
		//Path filePath = Paths.get(filePathString);
		
		File file = new File(getServletContext().getRealPath("/"+"file_upload" +File.separator + fileName));
		
		//get file type
		String fileType = Files.probeContentType(file.toPath().toAbsolutePath());
		
		//get file category
		String fileCategory  = fileType.substring(0, fileType.lastIndexOf("/"));
		
		//get file size
		String fileSize = Integer.toString((int) filePart.getSize());
		
		//get timestamp
		Date date = new Date();
		Timestamp timestamp = new Timestamp(date.getTime());
		String currentTimeStamp = Integer.toString((int) timestamp.getTime());
		
		//get file hash
		String fileHash =  currentTimeStamp+generateRandomChars(
	            "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890", 10);
		

		//File upload here
		for (Part part : request.getParts()) {
		      part.write(getServletContext().getRealPath("/"+"file_upload" +File.separator + fileNewName));
		    }
		
		try
		{
			
			//AES encryption		
			Cipher aesCipher = Cipher.getInstance("AES");
			KeyGenerator aesKeyGenerator = KeyGenerator.getInstance("AES");
			aesKeyGenerator.init(128);
			Key aesKey = aesKeyGenerator.generateKey();
			aesCipher.init(Cipher.ENCRYPT_MODE, aesKey); 
			CipherInputStream aesCipt = new CipherInputStream(new FileInputStream(new File(filePathString)), aesCipher);
			FileOutputStream aesFileip = new FileOutputStream(new File(encryptedFilePathString));
	
			int i;
			while((i=aesCipt.read())!=-1)
			{
				aesFileip.write(i);
	
			}
			
			
			//RSA encryption
			//Create a Cipher object
			Cipher rsaCipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
			KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
	        generator.initialize(1024);
	        KeyPair pair = generator.generateKeyPair();
	        PrivateKey privateKey = pair.getPrivate();
	        PublicKey publicKey = pair.getPublic();
	        
			String encryptedAesKey = encryptRSA(aesKey.getEncoded(), publicKey, rsaCipher);
			String encryptedPublicKey = encode(publicKey.getEncoded());
			String encryptedPrivateKey = encode(privateKey.getEncoded());
			
			
			//Insert file data into the database
			
			//create file object here
			Filex newFile = new Filex(userId, folder_id, fileNameDb, fileNewName, fileType, fileCategory, fileSize, fileHash, filePathString, encryptedAesKey, encryptedPublicKey, encryptedPrivateKey);
			
			boolean upload_status;
			try {
				upload_status = fileDbUtil.createFile(newFile);
				
				if(upload_status) {
					
						json.put("status", "success");
						json.put("message", "file successfully uploaded");
			    	out.println(json);
			    	
			    	System.out.println("encryptedAesKey: " +encryptedAesKey);
					System.out.println("encryptedPublicKey: " +encryptedPublicKey);
					System.out.println("encryptedPrivateKey: " +encryptedPrivateKey);
					System.out.println("aesKey: " +aesKey);
					
			    }else {
			    	json.put("status", "error");
					json.put("message", "file upload error");
			    	out.println(json);
			    }
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch(Exception e){
			e.printStackTrace();
		}	
	}
	
	public String generateRandomChars(String candidateChars, int length) {
		StringBuilder sb = new StringBuilder();
	    Random random = new Random();
	    for (int i = 0; i < length; i++) {
	        sb.append(candidateChars.charAt(random.nextInt(candidateChars
	                .length())));
	    }

	    return sb.toString();
	}
	
	private byte[] decode(String data){
        return java.util.Base64.getDecoder().decode(data);
    }
	
	private String encode(byte[] data){
        return java.util.Base64.getEncoder().encodeToString(data);
    }
	
	public String encryptRSA(byte[] aesKey, PublicKey publicKey, Cipher rsaCipher) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException
	{  

	    //Initialize the cipher for encryption. Use the public key.
	    rsaCipher.init(Cipher.ENCRYPT_MODE, publicKey);
	    
	    //Perform the encryption using doFinal
	    byte[] encByte = rsaCipher.doFinal(aesKey);
	    System.out.println("encryptedMessage: "+encByte);
	    // converts to base64 String for easier display and storage.
	    return encode(encByte);
	}

	public Key decryptRSA(PrivateKey privateKey, String encryptedAesKey) throws IllegalBlockSizeException, BadPaddingException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException
	{

	    //Create a Cipher object
	    Cipher rsaCipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");

	    //Initialize the cipher for encryption. Use the public key.
	    rsaCipher.init(Cipher.DECRYPT_MODE, privateKey);

	    //Decode Base64 String to byte
	    byte[] decByte = decode(encryptedAesKey);
	    
	    //Perform the decryption using doFinal
	    byte[] decryptedMessage = rsaCipher.doFinal(decByte);
	    
	    System.out.println("decryptedMessage: "+decryptedMessage);
	    //Get Key from bytedecryptedMessage
	    Key key = new SecretKeySpec(decryptedMessage, "AES");

	    return key;
	}
	
	public void decryptAES(String encryptedAesKey, String privateKey, String publicKey, String encryptedFilePathString, String decryptedFilePathString) throws InvalidKeyException, IOException, IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException {
		
		// Cipher		
		Cipher aesCipher = Cipher.getInstance("AES");
		
		//X509EncodedKeySpec keySpecPublic = new X509EncodedKeySpec(decode(publicKey));
        PKCS8EncodedKeySpec keySpecPrivate = new PKCS8EncodedKeySpec(decode(privateKey));
        
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        
        //PublicKey decryptedPublicKey = keyFactory.generatePublic(keySpecPublic);
        PrivateKey decryptedPrivateKey = keyFactory.generatePrivate(keySpecPrivate);
        
        //System.out.println("decryptedPublicKey: " +decryptedPublicKey);
        //System.out.println("decryptedPrivateKey: " +decryptedPrivateKey);
        
		// Decrypt AES key
		Key key = decryptRSA(decryptedPrivateKey, encryptedAesKey);
		System.out.println("aesKey: " +key);
		aesCipher.init(Cipher.DECRYPT_MODE, key);
		  
		CipherInputStream ciptt=new CipherInputStream(new FileInputStream(new File(encryptedFilePathString)), aesCipher);

		FileOutputStream fileop=new FileOutputStream(new File(decryptedFilePathString));

		int j;
		while((j=ciptt.read())!=-1)
		{
		fileop.write(j);
		}
	}
	
}
