package resources;

import pojo.AddPlace;
import pojo.Location;

import java.util.ArrayList;
import java.util.List;

public class TestDataBuild {
    public  AddPlace addPlacePayLoad(String name, String language, String address){
        AddPlace addPlace = new AddPlace();
        Location location = new Location();
        location.setLat(-38.383494);
        location.setLng(33.427362);
        addPlace.setLocation(location);

        addPlace.setAccuracy(50);
        addPlace.setName(name);
        addPlace.setPhone_number("(+91) 983 893 3937");
        addPlace.setAddress(address);
        addPlace.setLanguage(language);
        addPlace.setWebsite("http://google.com");

        List<String> ls = new ArrayList<String>();
        ls.add("shoe park");
        ls.add("shop");
        addPlace.setTypes(ls);
        return addPlace;
    }

    public String deletePlacePayload(String place_id){
        return "{\"place_id\":\""+place_id+"\"}";
    }
}
