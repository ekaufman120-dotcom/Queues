import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.Field;
import java.util.ArrayList;

/**
 * This class tests both the ArrayQueue and LinkedQueue classes
 * 
 * @author Mr. Bredemeier
 * @version November 4, 2012
 */

public class QueueTest<T>
{
    private static boolean failed = false;
    private static boolean arrayFailed, linkedFailed;
    private static ArrayList<String> methodNames = new ArrayList<String>();
    private static QueueInterface<Integer> queue;
    private static String message;
    private static String methodName;
    private static Class<?> c;
    private static boolean display = true;  // turns error display messages on or off

    public static void main(String [] args)
    {
        testInterface();
        testQueue("ArrayQueue");
        arrayFailed = failed;
        failed = false;
        testQueue("LinkedQueue");   
        linkedFailed = failed;
        if(arrayFailed || linkedFailed)
            System.out.println("Bummer, the Queue assignment is not complete.  Try again");
        else
            System.out.println("Beautimous!  You have completed the Queue assignment");
    }
    
    private static void testQueue(String type)
    {
        queueTest(type);
        if(failed)
            System.out.println(type + " class does not pass the test\n");
    }
    
    private static void testInterface()
    {
        // make sure that QueueInterface<T> is untouched
        try 
        {
            c = Class.forName("QueueInterface");
            Member[] methods = c.getMethods();
            for (Member method : methods)
                methodNames.add(((Method)method).toGenericString());
        }
        catch(ClassNotFoundException e)
        {
            failure("Epic Failure: missing interface QueueInterface");
            return;
        }

        methodName = "public abstract void QueueInterface.clear()";
        if(!methodNames.contains(methodName))
            failure("Missing interface method: " + methodName);
        
        methodName = "public abstract int QueueInterface.size()";
        if(!methodNames.contains(methodName))
            failure("Missing interface method: " + methodName);

        methodName = "public abstract T QueueInterface.remove() throws QueueUnderflowException";
        if(!methodNames.contains(methodName))
            failure("Missing interface method: " + methodName);

        methodName = "public abstract T QueueInterface.add(T)";
        if(!methodNames.contains(methodName))
            failure("Missing interface method: " + methodName);

        methodName = "public abstract T QueueInterface.peek() throws QueueUnderflowException";
        if(!methodNames.contains(methodName))
            failure("Missing interface method: " + methodName);

        methodName = "public abstract boolean QueueInterface.empty()";
        if(!methodNames.contains(methodName))
            failure("Missing interface method: " + methodName);

        if(failed)
        {
            System.out.println("\nCorrupted interface QueueInterface");
        }
    }

    private static void queueTest(String type)
    {
        //********** Generic Queue Class Test **************************************
        System.out.println("Now testing your " + type + " class:");
        try 
        {
            if(type.equals("ArrayQueue"))
                queue = new ArrayQueue<Integer>();
            else
                queue = new LinkedQueue<Integer>();
            c = Class.forName(type);
        }
        catch(ClassNotFoundException e)
        {
            failure("Epic Failure: missing " + type + " class");
            return;
        }
        catch(NoClassDefFoundError e)
        {
            failure("Epic Failure: missing " + type + " class");
            return;
        }
        catch (NoSuchMethodError e)
        {
            failure("Failed: missing constructor " + type + "()");
            return;
        }        
        
        //make sure that ArrayQueue implements QueueInterface
        if(!(queue instanceof QueueInterface))
        {
            failure("Failed: " + type + " does not implement QueueInterface");
            return;
        }
        display(type + " implements QueueInterface test");

        //validate size method
        if (queue.size() != 0)
        {
            failure("Failed: incorrect size value");
            return;
        }

        //validate empty method
        if (!queue.empty())
        {
            failure("Failed: incorrect empty method");
            return;
        }
        
        // test add(element) method
        if(queue.add(new Integer(1)).intValue() != 1)
            failure("add(element) method is not correct");
            
        // validate size method
        if (queue.size() != 1)
        {
            failure("Failed: incorrect size value");
            return;
        }

        //validate empty method
        if (queue.empty())
        {
            failure("Failed: incorrect empty method");
            return;
        }
            
        // test peek method
        if(queue.peek().intValue() != 1)
            failure("peek method is not correct");
        
        // test remove method
        if(queue.remove().intValue() != 1)
            failure("remove method is not correct");
            
        // validate size method
        if (queue.size() != 0)
        {
            failure("Failed: incorrect size value");
            return;
        }

        //validate empty method
        if (!queue.empty())
        {
            failure("Failed: incorrect empty method");
            return;
        }
        
        //validate remove throwing QueueUnderflowException
        try
        {
            if(queue.remove() != null)
                failure("queue method is not correct");
        }
        catch (QueueUnderflowException e)
        {
            display("remove method correctly throws QueueUnderflowException");
        }
        catch (ArrayIndexOutOfBoundsException e)
        {
            failure("remove method does not correctly throw QueueUnderflowException");
        }
            
        //validate peek throwing QueueUnderflowException
        try
        {
            if(queue.peek() != null)
                failure("peek method is not correct");
        }
        catch (QueueUnderflowException e)
        {
            display("peek method correctly throws QueueUnderflowException");
        }
        catch (ArrayIndexOutOfBoundsException e)
        {
            failure("peek method does not correctly throw QueueUnderflowException");
        }
        
        for(int i = 10; i <= 1000; i += 10)
        {
            try
            {
                queue.add(new Integer(i));
            }
            catch (ArrayIndexOutOfBoundsException e)
            {
                failure("array does not dynamically expand");
                break;
            }
        }
        
        // validate size method
        if (queue.size() != 100)
        {
            failure("Failed: incorrect size value");
            return;
        }

        //validate empty method
        if (queue.empty())
        {
            failure("Failed: incorrect empty method");
            return;
        }
        
        int x = queue.peek().intValue();
        if(x != 10)
            failure("add and/or peek methods are incorrect");
        
        while(x < 1000)
        {
            if(x != queue.remove().intValue())
                failure("add and/or remove methods are incorrect");
            x += 10;
            if(x != queue.peek().intValue())
                failure("add and/or peek methods are incorrect");
        }

        // validate size method
        if (queue.size() != 1)
        {
            failure("Failed: incorrect size value");
            return;
        }

        //validate empty method
        if (queue.empty())
        {
            failure("Failed: incorrect empty method");
            return;
        }
        
        // test peek method
        if(queue.peek().intValue() != 1000)
            failure("peek method is not correct");
        
        // test remove method
        if(queue.remove().intValue() != 1000)
            failure("remove method is not correct");
            
        // validate size method
        if (queue.size() != 0)
        {
            failure("Failed: incorrect size value");
            return;
        }

        //validate empty method
        if (!queue.empty())
        {
            failure("Failed: incorrect empty method");
            return;
        }
        
        //validate remove throwing QueueUnderflowException
        try
        {
            if(queue.remove() != null)
                failure("remove method is not correct");
        }
        catch (QueueUnderflowException e)
        {
            display("remove method again correctly throws QueueUnderflowException");
        }
        catch (ArrayIndexOutOfBoundsException e)
        {
            failure("remove method does not correctly throw QueueUnderflowException");
        }
            
        //validate peek throwing QueueUnderflowException
        try
        {
            if(queue.peek() != null)
                failure("peek method is not correct");
        }
        catch (QueueUnderflowException e)
        {
            display("peek method again correctly throws QueueUnderflowException");
        }
        catch (ArrayIndexOutOfBoundsException e)
        {
            failure("peek method does not correctly throw QueueUnderflowException");
        }
        
        if(!failed)
        {
            display("size method is correct");
            display("empty method is correct");
            display("add method is correct");
            display("peek method is correct");
            display("remove method is correct");
        }
        
        for(int i = 0; i < 500; i++)
            queue.add(new Integer(i));
            
        queue.clear();
        
        // validate size method
        if (queue.size() != 0)
        {
            failure("Failed: incorrect size value");
            return;
        }

        //validate empty method
        if (!queue.empty())
        {
            failure("Failed: incorrect empty method");
            return;
        }
        
        //validate remove throwing QueueUnderflowException
        try
        {
            if(queue.remove() != null)
                failure("clear method is incorrect");
        }
        catch (QueueUnderflowException e)
        {
        }
        if(!failed)
            display("clear method is correct");
        
        if(!failed)
            System.out.println("Congratulations!  Your " + type + " class is correct\n");

    }
    
    private static void failure(String str)
    {
        if(display)
            System.out.println("*** " + str);
        failed = true;
    }
    
    private static void display(String str)
    {
        if(display)
            System.out.println("Passed " + str);
    }
}
