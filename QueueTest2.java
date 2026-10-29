public class QueueTest2 {
    public static void main(String[] args) {
        LinkedQueue<Integer> arr = new LinkedQueue<>();
        for(int i = 1; i <= 100; i++)
        {
            arr.add(i);
        }
        //System.out.println(arr.head());
        //System.out.println(arr.tail());
        System.out.println(arr.size());
        System.out.println(arr.remove());
        arr.add(5);
        System.out.println(arr.size());

        ArrayQueue<Integer> queue = new ArrayQueue<>();
        for(int i = 10; i <= 1000; i += 10)
        {
            try
            {
                queue.add(i);
            }
            catch (ArrayIndexOutOfBoundsException e)
            {
                break;
            }
        }
        System.out.println(queue.size());
        //System.out.println(arr.arr.length);
    }
}
