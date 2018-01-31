package nl.sogyo.webserver;

import java.util.*;

public class RequestMessage implements Request {
    private String[] firstLine;
    private List<String> requestData;
    private List<String> parametersNames = new ArrayList<String>();
    private List<String> parametersVals = new ArrayList<String>();
    private List<String> headerNames = new ArrayList<String>();
    private List<String> headerVals = new ArrayList<String>();

    RequestMessage(List<String> requestData){
        this.requestData = requestData;
        this.firstLine = requestData.get(0).split(" ");
        prepParameters();
        prepHeaderParameters();

//        System.out.println("You did a HTTP "+getHTTPMethod()+" request and requested the following resource: "+getResourcePath());
//        System.out.println("The following header parameters were passed:");
//        System.out.println();

//        System.out.println( getHTTPMethod() );
//        System.out.println( getResourcePath() );
//        System.out.println( getParameterNames() );
//        System.out.println( getParameterValue("test") );
//        System.out.println( getHeaderParameterValue("X-CookiesOK") );

    }

    /** Getters */
    @Override
    public HttpMethod getHTTPMethod() {
//        https://www.tutorialspoint.com/java/lang/enum_valueof.htm
        return HttpMethod.valueOf(firstLine[0]);
    }

    @Override
    public String getResourcePath() {
        return firstLine[1].split("\\?")[0];
    }
    @Override
    public List<String> getHeaderParameterNames() {
        return headerNames;
    }
    @Override
    public String getHeaderParameterValue(String name) {
        return headerVals.get(headerNames.indexOf(name));
    }
    @Override
    public List<String> getParameterNames() {
        return parametersNames;
    }
    @Override
    public String getParameterValue(String name) {
        return parametersVals.get(parametersNames.indexOf(name));
    }
    public String getHttpVersion() {
        return firstLine[2];
    }

    /** Methods */
    public void prepParameters(){
//        System.out.println( "############################################################" );
//        System.out.println( firstLine[1].split("\\?").length );
//        System.out.println( firstLine[1].split("\\?")[0] );
//        System.out.println( "############################################################" );

        // dit kan nog ff wat beter waarschijnlijk.
        if(firstLine[1].split("\\?").length > 1) {
            String parameters = firstLine[1].split("\\?")[1];
            String[] parametersArr = parameters.split("&");

            for (String line : parametersArr) {
                String[] s = line.split("=");
                parametersNames.add(s[0]);
                parametersVals.add(s[1]);
            }
        }
    }
    public void prepHeaderParameters(){
        for(int i = 1; i < requestData.size(); i++){
            headerNames.add( requestData.get(i).split(": ")[0] );
            headerVals.add( requestData.get(i).split(": ")[1] );
        }
    }
}
