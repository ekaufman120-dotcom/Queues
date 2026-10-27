public class LinkedQueue<T> implements QueueInterface<T>{

    public int head;
    public int tail;
    public T[] arr;

    public LinkedQueue()
    {
        head = 0;
        tail = 0;
        arr = (T[]) new Object[4];
    }    

    // returns the logical size of the queue
    public int size()
    {
        if(tail > head)
            return tail - head;
        return arr.length-1-head+tail;
    }    
    
    // tests if this queue is empty
    public boolean empty()
    {
        return arr[head]==null;
    }
    
    // adds an item onto the rear of this queue
    public T add(T item)
    {
        if(!this.full())
        {
            arr[tail] = item;
            tail++;
        }
        else
        {
            T[] newArr = (T[]) new Object[arr.length*2];
            int incrementor = 0;
            for(int i = head; i < arr.length; i++)
            {
                newArr[incrementor] = arr[i];
                incrementor++;
            }
            for(int i = 0; i < tail; i++)
            {
                newArr[incrementor] = arr[i];
                incrementor++;
            }
            arr = newArr;
            head = 0;
            tail = incrementor;
            add(item);
        }
        return item;
    }
        
    // looks at the object at the front of this queue
    // without removing it from the queue
    public T peek() throws QueueUnderflowException
    {
        if(arr[head]==null)
        {
            throw new QueueUnderflowException("Recieved value from array, got null.");
        }
        return arr[head];
    }
    
    // removes the object at the front of this queue 
    // and returns that object as the value of this function
    public T remove() throws QueueUnderflowException
    {
        if(arr[head]==null)
        {
            throw new QueueUnderflowException("Recieved value from array, got null.");
        }
        T temp = arr[head];
        arr[head]=null;
        if(head!=arr.length-1)
        {
            head++;
        }
        else
        {
            head = 0;
        }
        //shrinking the array
        if(arr.length>4&&this.size()<=arr.length/2)
        {
            T[] newArr = (T[]) new Object[arr.length/2];
            int incrementor = 0;
            if(tail > head)
            {
                for(int i = head; i < tail; i++)
                {
                    newArr[incrementor] = arr[i];
                    incrementor++;
                }
            }
            else
            {
                for(int i = head; i < arr.length; i++)
                {
                    newArr[incrementor] = arr[i];
                    incrementor++;
                }
                for(int i = 0; i < tail; i++)
                {
                    newArr[incrementor] = arr[i];
                    incrementor++;
                }
            }
            arr = newArr;
            head = 0;
            tail = incrementor;
        }
        return temp;
    }
    
    // removes all of the elements from this stack
    public void clear()
    {
        arr = (T[]) new Object[4];
        head = 0;
        tail = 0;
    }

    //OPTIONAL METHODS, NOT TESTED
    public boolean full()
    {
        return tail==head&&arr[head]!=null;
    }
}
