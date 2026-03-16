import java.util.*;

public class WebsiteAnalytics {

    HashMap<String,Integer> pageViews =
            new HashMap<>();

    HashMap<String,Set<String>> uniqueVisitors =
            new HashMap<>();

    HashMap<String,Integer> trafficSource =
            new HashMap<>();

    public void processEvent(
            String url,
            String user,
            String source){

        pageViews.put(url,
                pageViews.getOrDefault(url,0)+1);

        uniqueVisitors
                .computeIfAbsent(url,
                        k->new HashSet<>())
                .add(user);

        trafficSource.put(source,
                trafficSource.getOrDefault(source,0)+1);
    }

    public void displayStats(){

        System.out.println("Page Views:");

        for(String page:pageViews.keySet()){

            System.out.println(
                    page+" → "+
                            pageViews.get(page)+" views");

            System.out.println(
                    "Unique visitors: "+
                            uniqueVisitors.get(page).size());
        }

        System.out.println("\nTraffic Sources:");

        for(String src:trafficSource.keySet()){

            System.out.println(
                    src+" → "+
                            trafficSource.get(src));
        }
    }

    public static void main(String args[]){

        WebsiteAnalytics wa =
                new WebsiteAnalytics();

        wa.processEvent("/news","user1","google");

        wa.processEvent("/news","user2","facebook");

        wa.processEvent("/sports","user3","google");

        wa.processEvent("/news","user1","direct");

        wa.displayStats();
    }
}