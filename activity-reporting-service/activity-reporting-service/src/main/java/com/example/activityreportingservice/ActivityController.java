package com.example.activityreportingservice;


import org.springframework.web.bind.annotation.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Vector;
@RestController
public class ActivityController {

    private final HashMap<String, Vector<Point>> hmap = new HashMap<String, Vector<Point>>();


    @GetMapping("/activity/{key}/total")
    public Activity activity(@PathVariable String key) {
        double value = 0;
        if(hmap.containsKey(key)){
            Vector<Point> v = hmap.get(key);
            for(int j = 0; j < v.size(); ++j){
                if(v.get(j).getDate().after(new Date(System.currentTimeMillis() - 3600 * 1000 * 12))){
                    value += v.get(j).getValue();
                }
            }
        }
        Activity activity = new Activity(value);
        return activity;
    }

    @PostMapping(value = "/activity/{key}", consumes = "application/json", produces = "application/json")
    public void postActivity(@RequestBody Activity activity, @PathVariable String key){
        Point p = new Point(new Date(), activity.getValue());
        Vector<Point> v;
        if(hmap.containsKey(key)) {
            v = hmap.get(key);
        }  else {
            v = new Vector<>();
        }
        v.add(p);
        hmap.put(key, v);
    }
}

