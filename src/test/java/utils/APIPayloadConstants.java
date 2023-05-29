package utils;

import org.json.JSONObject;

public class APIPayloadConstants {

    //we will pass the body in multiple formats, so we have created this class

    public static String createEmployeePayload(){
        String createEmployeePayload = "{\n" +
                "    \"emp_firstname\": \"Bob\",\n" +
                "    \"emp_lastname\": \"Brown\",\n" +
                "    \"emp_middle_name\": \"Bobby\",\n" +
                "    \"emp_gender\": \"M\",\n" +
                "    \"emp_birthday\": \"2000-02-20\",\n" +
                "    \"emp_status\": \"Confirmed\",\n" +
                "    \"emp_job_title\": \"Engineer\"\n" +
                "}";
        return createEmployeePayload;
    }

    public static String createEmployeePayloadJson(){
        JSONObject obj = new JSONObject();
        obj.put("emp_firstname", "Bob");
        obj.put("emp_lastname", "Brown");
        obj.put("emp_middle_name", "Bobby");
        obj.put("emp_gender", "M");
        obj.put("emp_birthday", "2000-02-20");
        obj.put("emp_status", "Confirmed");
        obj.put("emp_job_title", "Engineer");

        return obj.toString();
    }

    public static String createEmployeePayloadDynamic(String fName, String lName, String mName, String gender, String birthday, String status, String jobTitle){
        JSONObject obj = new JSONObject();
        obj.put("emp_firstname", fName);
        obj.put("emp_lastname", lName);
        obj.put("emp_middle_name", mName);
        obj.put("emp_gender", gender);
        obj.put("emp_birthday", birthday);
        obj.put("emp_status", status);
        obj.put("emp_job_title", jobTitle);

        return obj.toString();
    }

    public static String updateEmployeePayloadJson(){
        JSONObject obj = new JSONObject();
        obj.put("employee_id", "57567A");
        obj.put("emp_firstname", "Jayysonn");
        obj.put("emp_lastname", "Brown");
        obj.put("emp_middle_name", "Bobby");
        obj.put("emp_gender", "M");
        obj.put("emp_birthday", "2000-02-20");
        obj.put("emp_status", "Confirmed");
        obj.put("emp_job_title", "Engineer");

        return obj.toString();
    }

    public static String partiallyUpdateEmployeePayloadJson(){
        JSONObject obj = new JSONObject();
        obj.put("employee_id", "57840A");
        obj.put("emp_job_title", "API Tester");

        return obj.toString();

    }
}
