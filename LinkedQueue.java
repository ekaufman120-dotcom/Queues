public class LinkedQueue<T> implements QueueInterface<T>{

    LLNode<T> head;

    public LinkedQueue()
    {
        head = new LLNode<T>(null);
    }    

    public LinkedQueue(T item)
    {
        head = new LLNode<T>(item);
    } 

    // returns the logical size of the queue
    public int size()
    {
        if(head.getInfo()==null)
        {
            return 0;
        }
        int size = 1;
        if(head.getLink()==null)
        {
            return 1;
        }
        LLNode<T> current = head;
        while(current.getLink()!=null)
        {
            size++;
            current = current.getLink();
        }
        return size;
    }    
    
    // tests if this queue is empty
    public boolean empty()
    {
        return head.getInfo()==null;
    }
    
    // adds an item onto the rear of this queue
    public T add(T item)
    {   
        if(item==null)
        {
            throw new NullPointerException("Attempted to add null value to queue");
        }
        if(head.getInfo()==null)
        {
            head.setInfo(item);
            return item;
        }
        LLNode<T> curNode = head;
        while(curNode.getLink()!=null)
            curNode = curNode.getLink();
        LLNode<T> newNode = new LLNode<T>(item);
        curNode.setLink(newNode);
        return item;
    }
        
    // looks at the object at the front of this queue
    // without removing it from the queue
    public T peek() throws QueueUnderflowException
    {
        if(head.getInfo()==null)
        {
            throw new QueueUnderflowException();
        }
        return head.getInfo();
    }
    
    // removes the object at the front of this queue 
    // and returns that object as the value of this function
    public T remove() throws QueueUnderflowException
    {
        T glungus = head.getInfo();
        if(head.getInfo()==null)
        {
            throw new QueueUnderflowException();
        }
        else if(head.getLink()==null)
        {
            head.setInfo(null);
        }
        else
            head = head.getLink();
        return glungus;
    }
    
    // removes all of the elements from this stack
    public void clear()
    {
        head = new LLNode<T>(null);
    }
}
