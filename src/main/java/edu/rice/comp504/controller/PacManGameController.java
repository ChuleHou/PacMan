package edu.rice.comp504.controller;


import static spark.Spark.*;
import com.google.gson.Gson;
import edu.rice.comp504.model.DispatchAdapter;
import edu.rice.comp504.model.PacManGameWorld;

public class PacManGameController {

    public static void main(String[] args) {
        staticFiles.location("/public");
        port(getHerokuAssignedPort());
        Gson gson = new Gson();

        PacManGameWorld gameWorld = new PacManGameWorld();
        DispatchAdapter dis = new DispatchAdapter(gameWorld);


        get("/update", (request, response) -> gson.toJson(dis.updateGameWorld(request.queryParams("userInput"))));

        post("/canvas/dims", (request, response) -> {
            PacManGameWorld.setCanvasDims(request.queryParams("height"),request.queryParams("width"));
            return gson.toJson("canvas dimensions");
        });

        get("/clear", (request, response) -> {
            dis.removeListeners();
            return gson.toJson("removed all balls and inner walls in paintobj world");
        });

        get("/nextFrame", (request, response) -> {

            return gson.toJson(dis.updateGameWorld(request.queryParams("userInput")));
            // get all object from game world
            // return a list of game world object
        });

    }

    /**
     * Get the Heroku assigned port number.
     * @return  Heroku assigned port number
     */
    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 4567; //return default port if heroku-port isn't set (i.e. on localhost)
    }

}
