package UTTT.bll.game;

import UTTT.bll.bot.IBot;
import UTTT.bll.field.IField;
import UTTT.bll.move.IMove;

/**
 * This is a proposed GameManager for Ultimate Tic-Tac-Toe, the implementation
 * of which is up to whoever uses this interface. Note that initializing a game
 * through the constructors means that you have to create a new instance of the
 * game manager for every new game of a different type (e.g. Human vs Human,
 * Human vs Bot or Bot vs Bot), which may not be ideal for your solution, so you
 * could consider refactoring that into an (re-)initialize method instead.
 *
 * @author mjl
 */
public class GameManager {

    /**
     * Three different game modes.
     */
    public enum GameMode {
        HumanVsHuman,
        HumanVsBot,
        BotVsBot
    }

    private final IGameState currentState;
    private int currentPlayer = 0; //player0 == 0 && player1 == 1
    private final static int PLAYER0 = 0;
    private final static int PLAYER1 = 1;
    private final static int DRAW = -2;
    private final static String macroDraw = "draw";
    private GameMode mode = GameMode.HumanVsHuman;
    private IBot bot = null;
    private IBot bot2 = null;
    private boolean microGridWon;
    private boolean microGridDraw;
    private String currentGameMode;
    
    /**
     * Set's the currentState so the game can begin. Game expected to be played
     * Human vs Human
     *
     * @param currentState Current game state, usually an empty board, but could
     * load a saved game.
     */
    public GameManager(IGameState currentState) {
        this.currentState = currentState;
        mode = GameMode.HumanVsHuman;
        currentGameMode = "PvP";
    }

    /**
     * Set's the currentState so the game can begin. Game expected to be played
     * Human vs Bot
     *
     * @param currentState Current game state, usually an empty board, but could
     * load a saved game.
     * @param bot The bot to play against in vsBot mode.
     */
    public GameManager(IGameState currentState, IBot bot) {
        this.currentState = currentState;
        mode = GameMode.HumanVsBot;
        this.bot = bot;
        currentGameMode = "PvAI";
    }

    /**
     * Set's the currentState so the game can begin. Game expected to be played
     * Bot vs Bot
     *
     * @param currentState Current game state, usually an empty board, but could
     * load a saved game.
     * @param bot The first bot to play.
     * @param bot2 The second bot to play.
     */
    public GameManager(IGameState currentState, IBot bot, IBot bot2) {
        this.currentState = currentState;
        mode = GameMode.BotVsBot;
        this.bot = bot;
        this.bot2 = bot2;
        currentGameMode = "AIvAI";
    }

    /**
     * User input driven Update
     *
     * @param move The next user move
     * @return Returns true if the update was successful, false otherwise.
     */
    public Boolean UpdateGame(IMove move) {
        //Verify the new move
        if (!VerifyMoveLegality(move)) {
            System.out.println("The move cannot be made!");
            return false;
        }

        //Update the currentState
        UpdateBoard(move);
        UpdateMacroboard(move);

        //Update currentPlayer
        currentPlayer = (currentPlayer + 1) % 2;

        return true;
    }

    /**
     * Non-User driven input, e.g. an update for playing a bot move.
     *
     * @return Returns true if the update was successful, false otherwise.
     */
    public Boolean UpdateGame() {
        //Check game mode is set to one of the bot modes.
        assert (mode != GameMode.HumanVsHuman);

        //Check if player is bot, if so, get bot input and update the state based on that.
        if (mode == GameMode.HumanVsBot && currentPlayer == 1)
        {
            //Check bot is not equal to null, and throw an exception if it is.
            assert (bot != null);

            IMove botMove = bot.doMove(currentState);

            //Be aware that your bots might perform illegal moves.
            return UpdateGame(botMove);
        }

        //Check bot is not equal to null, and throw an exception if it is.
        assert (bot != null);
        assert (bot2 != null);

        //TODO: Implement a bot vs bot Update.
        throw new UnsupportedOperationException("Not supported yet.");
    }

    private Boolean VerifyMoveLegality(IMove move) {
        //Test if the move is legal
        //NOTE: should also check whether the move is placed on an occupied spot.
        System.out.println("Checking move validity against macroboard available field");
        System.out.println("Not currently checking move validity actual board");
        if (currentState.getField().isInActiveMicroboard(move.getX(), move.getY())) {
            if (!checkIfOccupied(move.getX(), move.getY())) {
                return true;
            }
        }

        return false;
    }

    private void UpdateBoard(IMove move)
    {
        IField myfield = currentState.getField();
        String[][] board = myfield.getBoard();
        board[move.getX()][move.getY()] = currentPlayer + "";

        myfield.setBoard(board);
    }

    private void UpdateMacroboard(IMove move) {

        IField myfield = currentState.getField();
        String[][] macroBoard = myfield.getMacroboard();

        if (microBoardWon(move)) {
            macroBoard[move.getX() / 3][move.getY() / 3] = currentPlayer + "";
        } else if (microboardFull(move)) {
            macroBoard[move.getX() / 3][move.getY() / 3] = macroDraw;
        }

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (!(macroBoard[i][j].equals(PLAYER0 + "") || macroBoard[i][j].equals(PLAYER1 + "") || macroBoard[i][j].equals(macroDraw))) {
                    if (macroBoard[move.getX() % 3][move.getY() % 3].equals(PLAYER0 + "") || macroBoard[move.getX() % 3][move.getY() % 3].equals(PLAYER1 + "") 
                            || macroBoard[move.getX() % 3][move.getY() % 3].equals(macroDraw)) {
                        macroBoard[i][j] = "-1"; //means avalible field
                        continue;
                    }

                    if (i == move.getX() % 3 && j == move.getY() % 3) {
                        macroBoard[i][j] = "-1"; //means avalible field
                    } else {
                        macroBoard[i][j] = "."; //means empty field
                    }
                }
            }
        }
        myfield.setMacroboard(macroBoard);
    }

    /**
     * cheks if the spot is occupied
     *
     * @param move
     * @return true if occupied otherwise false
     */
    private boolean checkIfOccupied(int x, int y) {
        IField myField = currentState.getField();
        return myField.getPlayerId(x, y).equals(PLAYER0 + "") || myField.getPlayerId(x, y).equals(PLAYER1 + "");
    }

    /**
     * Check if the microboard in wich the move is played has been won
     *
     * @param move
     * @return true if the microboard has been won
     */
    private boolean microBoardWon(IMove move) {
        String[][] normalBoard = new String[3][3];

        int xMacro = move.getX() / 3;
        int yMacro = move.getY() / 3;

        String[][] nineBoard = currentState.getField().getBoard();

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                normalBoard[i][j] = nineBoard[xMacro * 3 + i][yMacro * 3 + j];
            }
        }
        return !checkIfNormalboardIsWon(normalBoard).equals(".");
    }

    /**
     * Check if a normal 3 by 3 board is won
     *
     * @param normalBoard a 3 by 3 two dimensional array
     * @return the winner if there is any else returns "."
     */
    private String checkIfNormalboardIsWon(String[][] normalBoard) {
        String[] line = new String[3];

        //vertical
        for (int i = 0; i < 3; i++) {
            System.arraycopy(normalBoard[i], 0, line, 0, 3);

            if (line[0].equals(line[1]) && line[1].equals(line[2])) {
                if (!(line[0].equals(".") || line[0].equals("-1"))) {
                    microGridWon = true;
                    return line[0];
                }
            }

        }

        //horizontal
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                line[j] = normalBoard[j][i];
            }

            if (line[0].equals(line[1]) && line[1].equals(line[2])) {
                if (!(line[0].equals(".") || line[0].equals("-1"))) {
                    microGridWon = true;
                    return line[0];
                }
            }

        }

        //diagnoal 1
        for (int i = 0; i < 3; i++) {
            line[i] = normalBoard[i][i];
        }
        if (line[0].equals(line[1]) && line[1].equals(line[2])) {
            if (!(line[0].equals(".") || line[0].equals("-1"))) {
                microGridWon = true;
                return line[0];
            }
        }
        //diagnoal 2
        for (int i = 0; i < 3; i++) {
            line[i] = normalBoard[2 - i][i];
        }
        if (line[0].equals(line[1]) && line[1].equals(line[2])) {
            if (!(line[0].equals(".") || line[0].equals("-1"))) {
                microGridWon = true;
                return line[0];
            }
        }

        return ".";

    }

    /**
     * Check if any players has won.
     *
     * @return boolean expressing rather or not it is game over.
     */
    public int isGameOver() {
        if (checkIfNormalboardIsWon(currentState.getField().getMacroboard()).equals(PLAYER0 + "")) {
            return PLAYER0;
        } else if (checkIfNormalboardIsWon(currentState.getField().getMacroboard()).equals(PLAYER1 + "")) {
            return PLAYER1;
        }
        else
        {
            String[][] macroBoard = currentState.getField().getMacroboard();
            for (int i = 0; i < 3; i++)
            {
                for (int j = 0; j < 3; j++)
                {
                    if (!(macroBoard[i][j].equalsIgnoreCase(PLAYER0 + "")
                            || macroBoard[i][j].equalsIgnoreCase(PLAYER1 + "")
                            || macroBoard[i][j].equalsIgnoreCase(macroDraw)))
                    {
                        return -1;
                    }
                }
            }
            return DRAW;
        }
    }

    /**
     * Checks if the the microboard is full
     * @param move
     * @return true if full with player0 and player 1 othervise false
     */
    private boolean microboardFull(IMove move) {
        String check;

        int xMacro = move.getX() / 3;
        int yMacro = move.getY() / 3;

        String[][] nineBoard = currentState.getField().getBoard();

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                check = nineBoard[xMacro * 3 + i][yMacro * 3 + j];

                if (!(check.equals(PLAYER0 + "") || check.equals(PLAYER1 + ""))) {
                    return false;
                }

            }
        }
        microGridDraw = true;
        return true;
    }
    
    public boolean isMicroGridWon() {
        return microGridWon;
    }

    public void setMicroGridWon(boolean microGridWon) {
        this.microGridWon = microGridWon;
    }

    public boolean isMicroGridDraw() {
        return microGridDraw;
    }

    public void setMicroGridDraw(boolean microGridDraw) {
        this.microGridDraw = microGridDraw;
    }
    
    public String getGameMode() {
        return currentGameMode;
    }
    

}
