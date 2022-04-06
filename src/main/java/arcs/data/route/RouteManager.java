package arcs.data.route;

import arcs.data.exception.ArcsException;
import arcs.data.exception.DuplicateDataException;

import java.util.ArrayList;

public class RouteManager {
    private ArrayList<Route> routes;

    public RouteManager() {
        routes = new ArrayList<>();
    }

    public RouteManager(ArrayList<Route> routes) {
        this.routes = routes;
    }

    public void addRoute(Route newRoute) throws DuplicateDataException {
        boolean hasDuplicateId = hasDuplicateFlightId(newRoute);
        if (hasDuplicateId) {
            throw new DuplicateDataException("The flight ID is already occupied.");
        }

        routes.add(newRoute);
    }

    public ArrayList<Route> getAllRoutes() {
        return routes;
    }

    public Route deleteRoute(int index) throws ArcsException {
        assert routes != null : "Routes is null";
        if (index <= 0 || index > routes.size()) {
            throw new ArcsException("Index out of bound.");
        }
        Route deleted = routes.get(index - 1);
        routes.remove(index - 1);
        return deleted;
    }

    public ArrayList<Route> findRoute(String date, String to, String from) {
        ArrayList<Route> result = new ArrayList<>();
        for (Route route: routes) {
            Boolean dateMatch = route.getDate().equals(date);
            Boolean toMatch = route.getTo().equals(to);
            Boolean fromMatch = route.getFrom().equals(from);
            if (dateMatch && toMatch && fromMatch) {
                result.add(route);
            }
        }
        return result;
    }

    public ArrayList<Route> findRoute(String date, String to, String from, String time) {
        ArrayList<Route> result = new ArrayList<>();
        for (Route route: routes) {
            Boolean dateMatch = route.getDate().equals(date);
            Boolean toMatch = route.getTo().equals(to);
            Boolean fromMatch = route.getFrom().equals(from);
            Boolean timeMatch = route.getTime().equals(time);
            if (dateMatch && toMatch && fromMatch && timeMatch) {
                result.add(route);
            }
        }
        return result;
    }

    public Route findRoute(String fid) {
        for (Route route: routes) {
            if (route.getFlightID().equals(fid)) {
                return route;
            }
        }
        return null;
    }

    public boolean hasDuplicateFlightId(Route newRoute) {
        String newId = newRoute.getFlightID();
        for (Route route: routes) {
            if (route.getFlightID().equals(newId)) {
                return true;
            }
        }
        return false;
    }

}
