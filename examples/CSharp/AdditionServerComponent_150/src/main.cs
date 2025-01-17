

using System;
using System.Threading;

public class MainClass
{
	// Boolean to determine if program should keep running.
	private static bool run = true;

    public static void Main()
    {
        // Instantiate the component and start it.
        AdditionServerComponent cmpt = new AdditionServerComponent(126, 1, 150);
	
        // Start the component and the services
        cmpt.startComponent();
	    
        // Wait until signaled to exit
	   Console.CancelKeyPress += delegate(object sender, ConsoleCancelEventArgs e)
	   {
			e.Cancel = true;
			MainClass.run = false;
	   };
	   
	   while(run)
	   {
			Thread.Sleep(100);
	   }
    
        // Shutdown the component and threads
        cmpt.shutdownComponent();
    }
	
	protected static void exit_handler(object sender, ConsoleCancelEventArgs args)
	{
		args.Cancel = true;
	}
}
