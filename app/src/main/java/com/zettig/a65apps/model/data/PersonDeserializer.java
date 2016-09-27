package com.zettig.a65apps.model.data;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class PersonDeserializer implements JsonDeserializer<Person> {
    @Override
    public Person deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Person person = new Person();
        JsonObject jsonObject = json.getAsJsonObject();
        person.setFName(firstUpperCase(jsonObject.get("f_name").getAsString()));
        person.setLName(firstUpperCase(jsonObject.get("l_name").getAsString()));

        if (!jsonObject.get("avatr_url").isJsonNull()){
            String avatr = jsonObject.get("avatr_url").getAsString();
            if (isNotNull(avatr)){
                person.setAvatrUrl(avatr);
            } else person.setAvatrUrl(null);
        }



        if (!jsonObject.get("birthday").isJsonNull()){
            String bd = jsonObject.get("birthday").getAsString();
            if (isNotNull(bd)){
                try {
                    person.setBirthday(toDate(bd));
                    person.setAge(calculateAge(person.getBirthday()));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        }
        JsonArray specialtys = jsonObject.getAsJsonArray("specialty");
        for (JsonElement specialty:specialtys){
            person.addSpecialty((Specialty)context.deserialize(specialty,Specialty.class));
        }
        return person;
    }
    private static String firstUpperCase(String word) {
        if (word == null || word.isEmpty()) return "";
        return word.substring(0, 1).toUpperCase() + word.substring(1).toLowerCase();
    }
    private static Date toDate(String d) throws ParseException {
        SimpleDateFormat df;
        String[] parts = d.split("-");
        if (parts[0].length()== 4) {
            df = new SimpleDateFormat("yyyy-MM-dd");
        }else {
            df = new SimpleDateFormat("dd-MM-yyyy");
        }

        return df.parse(d);
    }
    private boolean isNotNull(String string){
        if (string==null || string.isEmpty()){
            return false;
        } else
            return true;
    }
    public static int calculateAge(Date bDay) {
        int age;
        Calendar dob = Calendar.getInstance();
        Calendar today = Calendar.getInstance();

        dob.setTime(bDay);
        dob.add(Calendar.DAY_OF_MONTH, -1);

        age = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR);
        if (today.get(Calendar.DAY_OF_YEAR) <= dob.get(Calendar.DAY_OF_YEAR)) {
            age--;
        }
        return age;
    }
}
