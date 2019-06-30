package main.java.be.krivi.whichap;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Locale;

final class Utils{

    private static OS detectedOS;

    static OS getOS(){
        if( detectedOS == null ){
            String os = System.getProperty( "os.name", "generic" ).toLowerCase( Locale.ENGLISH );

            if( ( os.contains( "mac" ) ) || ( os.contains( "darwin" ) ) )
                detectedOS = OS.MACOS;
            else if( os.contains( "win" ) )
                detectedOS = OS.WINDOWS;
            else if( os.contains( "nux" ) )
                detectedOS = OS.LINUX;
            else
                detectedOS = OS.OTHER;
        }
        return detectedOS;
    }

    static String getBSSID(){
        if( detectedOS == null )
            getOS();
        try{
            switch( detectedOS ){
                case MACOS:
                    return getBSSID( new String[]{ "/bin/sh", "-c", "/System/Library/PrivateFrameworks/Apple80211.framework/Versions/Current/Resources/airport -I | grep BSSID" } );
                case WINDOWS:
                    return getBSSID( new String[]{ "cmd", "/c", "netsh wlan show interfaces | findstr BSSID" } );
                default:
                    return "Sorry, this OS is not supported.";
            }
        }catch( Exception e ){
            return "Looks like you're not connected to Wi-Fi.";
        }
    }

    private static String getBSSID( String[] cmd ) throws Exception{
        Process result = Runtime.getRuntime().exec( cmd );
        BufferedReader output = new BufferedReader( new InputStreamReader( result.getInputStream() ) );
        return output.readLine().trim().toUpperCase().replaceAll( "BSSID[ ]*: ", "" );
    }

    private enum OS{
        LINUX, MACOS, WINDOWS, OTHER
    }
}
