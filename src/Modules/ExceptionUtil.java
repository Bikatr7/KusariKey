package Modules;

//--------------------start-of-exceptionUtil------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

public class ExceptionUtil 
{
    
//--------------------start-of-exceptionUtil()------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

private ExceptionUtil()
{
    throw new UnsupportedOperationException("This is a static class and cannot be instantiated.");
}

//-------------------start-of-ExceptionalRunnable---------------------------------------------------------------------------------------------------------------------------------------------------------------------------

    @FunctionalInterface
    public interface ExceptionalRunnable<E extends Exception> 
    {
        void run() throws E;
    }

//-------------------start-of-handleCriticalException()---------------------------------------------------------------------------------------------------------------------------------------------------------------------------

    /**
     * Handles a critical exception.
     * @param e Exception - the exception we are handling
     */

    public static void handleCriticalException(Exception e)
    {
        System.out.println("Critical Exception: " + e.getMessage());

        for (StackTraceElement ste : e.getStackTrace()) 
        {
            System.out.println(ste.toString());
        }

        System.out.println("\nKusariKey will now exit");

        Toolkit.pauseConsole();

        exitKusariKey();
        
    }

//-------------------start-of-exitKusariKey()---------------------------------------------------------------------------------------------------------------------------------------------------------------------------

    /**
     * Exits KusariKey.
     */

    public static void exitKusariKey()
    {
        Toolkit.clearConsole();

        System.out.println("Exiting KusariKey...");

        Logger.pushBatch();

        System.exit(0);
    }

}