package com.example.fooddeliveryapp.Adapter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

public class CharSequenceTypeAdapter extends TypeAdapter<CharSequence> {
    Gson gson = new GsonBuilder()
            .registerTypeAdapter(CharSequence.class, new CharSequenceTypeAdapter())
            .create();
    @Override
    public void write(JsonWriter out, CharSequence value) throws IOException {
        out.value(value.toString());
    }

    @Override
    public CharSequence read(JsonReader in) throws IOException {
        return in.nextString(); // Return a String instance
    }
}
