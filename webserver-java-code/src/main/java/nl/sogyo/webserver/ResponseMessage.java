package nl.sogyo.webserver;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class ResponseMessage implements Response {
    private RequestMessage req;
    private ZonedDateTime dateTime;
    private HttpStatusCode statusCode;

    private int contentLength;
    private String body;
    private String res;

    ResponseMessage(RequestMessage req){
        this.req = req;
        this.statusCode = HttpStatusCode.OK;
        this.body = makeBody();
        this.res = getRes();

        System.out.println(this.res);
    }

    /** Getters */
    @Override
    public HttpStatusCode getStatus() {
        return statusCode;
    }

    private String getHttpStatusCode() {
        return this.statusCode.getCode() + " " + this.statusCode.getDescription();
    }

    @Override
    public ZonedDateTime getDate() {
        return ZonedDateTime.now();
    }

    public int getContentLength() {
        return this.body.trim().length();
    }

    @Override
    public String getContent() {
        return this.body;
    }

    @Override
    public void setContent(String content) {

    }

    /** Setters */
    @Override
    public void setStatus(HttpStatusCode status) {
        statusCode = status;
    }

    /** Methods */
    private String makeBody(){
        StringBuilder tempBody = new StringBuilder();
        tempBody.append("\n");
        tempBody.append("<html>\n");
        tempBody.append("<body>\n");
        tempBody.append("You did a <span style='color:green; font-weight: bold; font-family:arial;'>HTTP " + req.getHTTPMethod() + " </span>request <br/>\n");
        tempBody.append("You requested resource: " + req.getResourcePath() + "<br/><br/>\n");

        if(!req.getParameterNames().isEmpty()) {
            tempBody.append("Params:<br/>\n");

            for (String param : req.getParameterNames()) {
                tempBody.append("&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp"+param + ": " + req.getParameterValue(param) + "<br/>\n");
            }
        }

        tempBody.append("</body>\n");
        tempBody.append("</html>\n");
        return tempBody.toString();
    }

    public String getRes(){
        StringBuilder res = new StringBuilder();
        res.append(req.getHttpVersion() + " " + this.getHttpStatusCode() + "\n");
        res.append("Date: " + this.getDate().format(DateTimeFormatter.ofPattern("EEE, dd MMM yyyy HH:mm:ss O")) + "\n");
        res.append("Server: Blablabla" + "\n");
        res.append("Connection: " + req.getHeaderParameterValue("Connection") + "\n");
        res.append("Content-Type: text/html; charset=UTF-8" + "\n");
        res.append("Content-Length: " + getContentLength() + "\n");
        res.append(this.body);
        return res.toString();
    }
}
