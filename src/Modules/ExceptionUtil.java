package Modules;

//--------------------start-of-exceptionUtil------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

public class ExceptionUtil 
{
    /**
     * This class is used to handle exceptions.
     */

//--------------------start-of-exceptionUtil()------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

private ExceptionUtil()
{
    throw new UnsupportedOperationException("This is a static class and cannot be instantiated.");
}

//-------------------start-of-ExceptionalRunnable---------------------------------------------------------------------------------------------------------------------------------------------------------------------------

    /**
     * This interface is used to run a critical try-catch block.
     * Implement this interface to use the criticalTryCatch() method.
     * Implementations of this interface should throw an exception.
     */

    @FunctionalInterface
    public interface ExceptionalRunnable<E extends Exception> 
    {
        void run() throws E;
    }

//-------------------start-of-critcalTryCatch()---------------------------------------------------------------------------------------------------------------------------------------------------------------------------

    /**
     * Runs a critical try-catch block.
     * @param runnable ExceptionalRunnable - the runnable to be run.
     */

    public static <E extends Exception> void criticalTryCatch(ExceptionalRunnable<E> runnable) 
    {
        try 
        {
            runnable.run();
        } 
        catch (Exception e) 
        {
            handleCriticalException(e);
        }
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