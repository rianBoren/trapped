package com.trapped.player;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class PlayerTest {
    Player player;

    @Before
    public void setUp() throws Exception {
        player = Player.getInstance();
    }

    @Test
    public void moveDirectionShouldReturnAMap() {
        Map<String, String> result = player.moveDirection("left");
        System.out.println(result);

        assertNotNull(result.get("description"));
        assertNotNull(result.get("picture"));
    }

    @Test
    public void solveUseToolShouldReturnAPuzzleDescription_IfGivenTheCorrectTool() {
        Player.location = "window";
        Map<String, String> result = player.solveUseTool( "crowbar");
        assertNotNull(result.get("puzzleDescription"));
    }

    @Test
    public void solveUseToolShouldReturnAError_IfGivenTheWrongToolToUse() {
        Map<String, String> result = player.solveUseTool( "matches");
        assertNotNull(result.get("error"));
    }
}