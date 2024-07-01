package api.endpoints;

public class Routes {
	
	public static String baseURI="https://petstore.swagger.io/v2/pet";
	
	public static String post_add_pet=baseURI;
	public static String post_upload_image=baseURI+"/{petid}/uploadImage";
	public static String put_update_pet=baseURI;
	public static String get_petbyStatus=baseURI+"/findByStatus";  //available, pending,sold. these need to be handled by query parameter
	public static String get_petbyid=baseURI+"/{petid}";
	public static String post_petupdatewithformdata=baseURI+"/{petid}";
	public static String delete_pet=baseURI+"/{petid}";

}
