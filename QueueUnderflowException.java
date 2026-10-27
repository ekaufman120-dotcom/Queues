public class QueueUnderflowException extends RuntimeException{

    public QueueUnderflowException()
    {
        super();
    }

    public QueueUnderflowException(String mes)
    {
        super(mes);
    }
}
