package p.sby.gs_qca.Main.Activity;

import android.app.Application;

import com.tencent.smtt.sdk.QbSdk;

import org.json.JSONArray;

import p.sby.gs_qca.util.ExceptionHandler;

public class global_variance extends Application {

   private String sessionid;
   private String username;
   private String courseid;
   private JSONArray department;
   private JSONArray Course;
   private JSONArray Searchlist;
   private JSONArray Draftlist;
    public void onCreate() {
        super.onCreate();
        //增加这句话
        QbSdk.initX5Environment(this,null);
        ExceptionHandler.getInstance().initConfig(this);
    }

    public String getSessionid() {
        return sessionid;
    }

    public void setSessionid(String sessionid) {
        this.sessionid = sessionid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public JSONArray getDepartment() {
        return department;
    }

    public void setDepartment(JSONArray department) {
        this.department = department;
    }

    public JSONArray getCourse() {
        return Course;
    }

    public void setCourse(JSONArray course) {
        Course = course;
    }

    public String getCourseid() {
        return courseid;
    }

    public void setCourseid(String courseid) {
        this.courseid = courseid;
    }

    public JSONArray getSearchlist() {
        return Searchlist;
    }

    public void setSearchlist(JSONArray searchlist) {
        Searchlist = searchlist;
    }

    public JSONArray getDraftlist(){
        return Draftlist;
    }

    public void setDraftlist(JSONArray draftlist){
        Draftlist=draftlist;
    }
}
