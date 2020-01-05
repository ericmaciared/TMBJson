package PlannerAPI;

public class Leg {
    private int distance;
    private int duration;
    private int endTime;

}
/*

{"startTime":1577804440000,
        "endTime":1577804459000,
        "departureDelay":0,
        "arrivalDelay":0,
        "realTime":false,
        "distance":23.382,
        "pathway":false,
        "mode":"WALK",
        "route":"",
        "agencyTimeZoneOffset":3600000,
        "interlineWithPreviousLeg":false,
        "from":{
            "name":"Origin",
            "lon":2.021744,
            "lat":41.403475,
            "departure":1577804440000,
            "orig":"","vertexType":"NORMAL"},
        "to":{
            "name":"Molins De Rei",
            "stopId":"4:72300",
            "lon":2.02057,
            "lat":41.409844,
            "arrival":1577804459000,
            "departure":1577804460000,
            "stopIndex":0,
            "stopSequence":1,
            "vertexType":"TRANSIT"},
        "legGeometry":{
            "points":"icv{FewkKOCIEMG",
            "length":4},
        "rentedBike":false,
        "duration":19,
        "transitLeg":false,
        "intermediateStops":[],
        "steps":[{
            "distance":23.382,
            "relativeDirection":"DEPART",
            "streetName":"track",
            "absoluteDirection":"NORTH",
            "stayOn":false,
            "area":false,"bogusName":true,"lon":2.0313999,"lat":41.406137,"elevation":[]}]}
            */