import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

public class Okey {

    public static void main(String[] args) {

        play();
    }

    private static void play() {

        List<Tile> initialTileList = new ArrayList<>();

        for(int countOfColour = 0; countOfColour < 2; countOfColour++) {

            prepareList(initialTileList);
        }

        Collections.shuffle(initialTileList);

        Tile okeyTile = pickTheOkeyTile(initialTileList);

        List<Player> playerList = createPlayerList();

        distributeTilesToPlayers (playerList, initialTileList);

        for (int i = 0; i < playerList.size(); i++) {

            System.out.println((i + 1) + ". Oyuncu\n");

            for(int j = 0; j < playerList.get(i).getTileList().size(); j++) {
                System.out.println(playerList.get(i).getTileList().get(j).getSuit() + ", " + playerList.get(i).getTileList().get(j).getValue());
            }
        }
    }

    private static void prepareList(List<Tile> initialTileList) {

        fillList(initialTileList, Colour.YELLOW.getValue());

        fillList(initialTileList, Colour.BLUE.getValue());

        fillList(initialTileList, Colour.BLACK.getValue());

        fillList(initialTileList, Colour.RED.getValue());

        initialTileList.add(new Tile(Colour.YELLOW.getValue(), 14));
    }

    private static void fillList(List<Tile> initialTileList, String colour) {

        for(int tileCount = 1; tileCount < 14; tileCount++) {
            initialTileList.add(new Tile(colour, tileCount));
        }
    }

    private static Tile pickTheOkeyTile(List<Tile> initialList) {

        Random rand = new Random();

        int randomIndex = rand.nextInt(initialList.size());

        Tile okeyTile = initialList.get(randomIndex);

        initialList.remove(randomIndex);

        return okeyTile;
    }

    private static List<Player> createPlayerList() {

        List<Player> playerList = new ArrayList<>();

        for(int playerCount = 0; playerCount < 4; playerCount++) {

            playerList.add(new Player(Integer.toString((playerCount+1))));
        }

        return playerList;
    }

    private static void distributeTilesToPlayers (List<Player> playerList, List<Tile> tileList) {

        Random rand = new Random();

        List<Tile> tileListForPlayer = new ArrayList<>();

        for(int tileCount = 0; tileCount < 15; tileCount++)
        {
            int randomIndex = rand.nextInt(tileList.size());

            tileListForPlayer.add(tileList.get(randomIndex));

            tileList.remove(randomIndex);
        }

        List<Player> playerIndexList = new ArrayList<>();

        for(int playerCount = 0; playerCount < 4; playerCount++) {
            playerIndexList.add(new Player(Integer.toString(playerCount+1)));
        }

        int randomIndexOfPlayer = rand.nextInt(playerIndexList.size());

        List<Tile> copyOfTileListForPlayer = tileListForPlayer.stream()
                .collect(Collectors.toList());

        playerList.get(randomIndexOfPlayer).setTileList(copyOfTileListForPlayer);

        Player deletedPlayer = playerIndexList.stream().filter(x-> x.getName().equals(Integer.toString(randomIndexOfPlayer))).findFirst().get();

        playerIndexList.remove(deletedPlayer);

        tileListForPlayer.clear();

        for(int i = 0; i < 3; i++ ) {
            a(playerList, tileList, tileListForPlayer, playerIndexList);
        }
    }

    private static void a(List<Player> playerList, List<Tile> tileList, List<Tile> tileListForPlayer, List<Player> playerIndexList) {

        Random rand = new Random();

        for(int tileCount = 0; tileCount < 14; tileCount++)
        {
            int randomIndex = rand.nextInt(tileList.size());

            tileListForPlayer.add(tileList.get(randomIndex));

            tileList.remove(randomIndex);
        }

        int randomIndexOfPlayer = rand.nextInt(playerIndexList.size());

        List<Tile> copyOfTileListForPlayer = tileListForPlayer.stream()
                .collect(Collectors.toList());

        playerList.stream().filter(x-> x.getName().equals(Integer.toString(randomIndexOfPlayer))).findFirst().get().setTileList(copyOfTileListForPlayer);

        Player deletedPlayer = playerIndexList.stream().filter(x-> x.getName().equals(Integer.toString(randomIndexOfPlayer))).findFirst().get();

        playerIndexList.remove(deletedPlayer);

        tileListForPlayer.clear();
    }
}
