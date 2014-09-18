package com.company;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;

enum State {
    reading, readingTag, awaitsH, awaitsR, awaitsE, awaitsF, awaitsEquality, awaitsLink,
    readingLinkDouble, readingLinkSingle
}

class allHrefs {
    State currentState = State.reading;
    private ArrayList<String> links = new ArrayList<String>();

    public ArrayList<String> getHrefs() {
        return links;
    }

    public allHrefs(BufferedReader source) throws IOException {
        String cString;
        StringBuilder sb = new StringBuilder();
        while ((cString = source.readLine()) != null) {

            for (int i = 0; i < cString.length(); i++) {

                char cChar = cString.charAt(i);
                
                switch (cChar) {
                    case '<':
                        if (currentState == State.reading) {
                            currentState = State.readingTag;
                        }
                        break;
                    case 'a':
                        if (currentState == State.readingLinkDouble || currentState == State.readingLinkSingle) {
                            sb.append(cChar);
                        }
                        if (currentState == State.readingTag) {
                            currentState = State.awaitsH;
                        }
                        break;
                    case ' ':
                        if (currentState == State.awaitsH) {
                            currentState = State.awaitsH;
                        }
                        break;
                    case 'h':
                        if (currentState == State.readingLinkDouble || currentState == State.readingLinkSingle) {
                            sb.append(cChar);
                        }
                        if (currentState == State.awaitsH) {
                            currentState = State.awaitsR;
                        }
                        break;
                    case 'r':
                        if (currentState == State.readingLinkDouble || currentState == State.readingLinkSingle) {
                            sb.append(cChar);
                        }
                        if (currentState == State.awaitsR) {
                            currentState = State.awaitsE;
                        }
                        break;
                    case 'e':
                        if (currentState == State.readingLinkDouble || currentState == State.readingLinkSingle) {
                            sb.append(cChar);
                        }
                        if (currentState == State.awaitsE) {
                            currentState = State.awaitsF;
                        }
                        break;
                    case 'f':
                        if (currentState == State.readingLinkDouble || currentState == State.readingLinkSingle) {
                            sb.append(cChar);
                        }
                        if (currentState == State.awaitsF) {
                            currentState = State.awaitsEquality;
                        }
                        break;
                    case '=':
                        if (currentState == State.readingLinkDouble || currentState == State.readingLinkSingle) {
                            sb.append(cChar);
                        }
                        if (currentState == State.awaitsEquality) {
                            currentState = State.awaitsLink;
                        }
                        break;
                    case '\"':
                        if (currentState == State.readingLinkDouble) {
                            links.add(sb.toString());
                            sb = new StringBuilder();
                            currentState = State.reading;
                        }
                        if (currentState == State.awaitsLink) {
                            currentState = State.readingLinkDouble;
                        }
                        break;
                    case '\'':
                        if (currentState == State.readingLinkSingle) {
                            links.add(sb.toString());
                            sb = new StringBuilder();
                            currentState = State.reading;
                        }
                        if (currentState == State.awaitsLink) {
                            currentState = State.readingLinkSingle;
                        }
                        break;
                    default:
                        if (currentState == State.readingLinkDouble || currentState == State.readingLinkSingle) {
                            sb.append(cChar);
                            break;
                        }
                        currentState = State.reading;
                        break;
                }
            }
        }
    }
}

public class MyHtmlParser {

    public static void main(String[] args) throws IOException {
        URL url = new URL("http://www.mon.gov.ua/");
        BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
        BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"));
        ArrayList<String> test = new allHrefs(reader).getHrefs();
        for (String s : test) {
            writer.write(s);
            writer.newLine();
        }
        reader.close();
        writer.close();
    }
}
