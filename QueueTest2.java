public class QueueTest2 {
    public static void main(String[] args) {
        ArrayQueue<Integer> arr = new ArrayQueue<>();
        for(int i = 1; i <= 4; i++)
        {
            arr.add(i);
            System.out.println(arr.peek());
        }
        System.out.println(arr.full());
        System.out.println(arr.head);
        System.out.println(arr.tail);
    }
}
