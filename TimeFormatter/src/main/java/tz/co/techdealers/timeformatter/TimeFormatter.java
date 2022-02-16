package tz.co.techdealers.timeformatter;


import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

public class TimeFormatter {
    private final long time;
    private long now;

    public TimeFormatter(long time) {
        this.time = time;
        this.now = new Date().getTime();
    }

    public String format() throws JSONException {

        Date myDate = new Date(this.time);
        Date now = new Date(this.now);

        JSONObject myDateObject = dateObject(myDate);
        JSONObject nowObject = dateObject(now);

        if (now.after(myDate)) {
            // Check year
            if (nowObject.getString("year").equals(myDateObject.getString("year"))) {
                // Same year

                // Check for month
                if (nowObject.getString("mon").equals(myDateObject.getString("mon"))) {
                    // Same month

                    // Check for date
                    if (nowObject.getString("date").equals(myDateObject.getString("date"))) {
                        // Same day

                        // Check hour
                        if (nowObject.getInt("hour") == myDateObject.getInt("hour")) {
                            // Same hour

                            // Check min
                            if (nowObject.getInt("min") == myDateObject.getInt("min")) {
                                // Same min

                                // Check sec
                                if (nowObject.getInt("sec") == myDateObject.getInt("sec"))
                                    return "now";
                                else {
                                    if (nowObject.getInt("sec") - myDateObject.getInt("sec") == 1) {
                                        return "1 second ago";
                                    } else {
                                        return nowObject.getInt("sec") - myDateObject.getInt("sec")+" seconds ago";
                                    }
                                }
                            } else {
                                if (nowObject.getInt("min") - myDateObject.getInt("min") == 1) {
                                    return "1 minute ago";
                                } else {
                                    return nowObject.getInt("min") - myDateObject.getInt("min")+" minutes ago";
                                }
                            }
                        } else {
                            if (nowObject.getInt("hour") - myDateObject.getInt("hour") == 1) {
                                return nowObject.getInt("hour") - myDateObject.getInt("hour") + " hour ago";
                            } else {
                                return nowObject.getInt("hour") - myDateObject.getInt("hour") + " hours ago";
                            }
                        }
                    }
                    // Yesterday
                    else if (checkYesterday(nowObject.getInt("date"), myDateObject.getInt("date"))) {
                        return formatTime(myDateObject)+" yesterday";
                    } else {
                        return formatTime(myDateObject)+" "+myDateObject.getString("date")+" "+myDateObject.getString("mon");
                    }
                } else {
                    return myDateObject.getString("date")+" "+myDateObject.getString("mon");
                }
            } else if (nowObject.getInt("year") - myDateObject.getInt("year") == 1) {
                return "1 year ago";
            } else {
                return nowObject.getInt("year") - myDateObject.getInt("year")+" years ago";
            }
        } return "Wrong date";
    }

    private boolean checkYesterday(int todayDate, int yestDate) {
        if (todayDate - yestDate == 1) {
            return true;
        } else {
            return false;
        }
    }


    private String formatTime(JSONObject object) throws JSONException {
        return object.getString("hour")+":"+object.getString("min");
    }

    public JSONObject dateObject(Date date) throws JSONException {
        String dateString = date.toString();
        String[] dateArray = dateString.split(" ");

        String[] clock = dateArray[3].split(":");

        JSONObject objectTime = new JSONObject();
        objectTime.put("day", dateArray[0]);
        objectTime.put("mon", dateArray[1]);
        objectTime.put("date", dateArray[2]);
        objectTime.put("hour", clock[0]);
        objectTime.put("min", clock[1]);
        objectTime.put("sec", clock[2]);
        objectTime.put("year", dateArray[5]);
        return objectTime;
    }

}
