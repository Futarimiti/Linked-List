import static java.lang.String.format;
import static java.lang.String.join;

public class CircularSinglyLinkedList<T>
{
	private static class Node<E>
	{
		E data;
		Node<E> next;

		Node(E data , Node<E> next)
		{
			this.data = data;
			this.next = next;
		}
	}

	private final Node<T> header = new Node<>(null , null);

	private Node<T> lastNode = header;
	private int size = 0;

	@SuppressWarnings("unchecked")
	public static <E> CircularSinglyLinkedList<E> circleOf(E... elems)
	{
		CircularSinglyLinkedList<E> res = new CircularSinglyLinkedList<>();
		for (E elem : elems)
		{
			res.append(elem);
		}

		return res;
	}

	public void append(T elem)
	{
		Node<T> newNode = null;
		if (header.next == null) newNode = new Node<>(elem , newNode);
		else newNode = new Node<>(elem , header.next);

		lastNode.next = newNode;

		this.lastNode = newNode;
		this.size++;
	}

	public int size()
	{
		return this.size;
	}

	public void remove(int pos)
	{
		if (pos < 0 || pos >= this.size)
		{
			String msg = format("Index: %d, Size: %d" , pos , this.size);
			throw new IndexOutOfBoundsException(msg);
		}

		Node<T> prev = header;
		for (int i = 1 ; i <= pos ; i++)
		{
			prev = prev.next;
		}

		prev.next = prev.next.next;

		if (pos == this.size - 1) lastNode = prev;
		this.size--;

		System.gc();
	}

	public void insert(T elem , int pos)
	{
		if (pos < 0 || pos > this.size)
		{
			String msg = format("Index: %d, Size: %d" , pos , this.size);
			throw new IndexOutOfBoundsException(msg);
		}

		Node<T> prev = header;
		for (int i = 1 ; i <= pos ; i++)
		{
			prev = prev.next;
		}

		Node<T> next = prev.next;

		Node<T> newNode = new Node<>(elem , next);
		prev.next = newNode;

		if (next == header.next) lastNode = newNode;
		this.size++;
	}

	public String toString(String sep)
	{
		String[] arr = new String[size];
		Node<T> cur = header;

		int i = 0;
		do
		{
			arr[i] = cur.next.data.toString();
			cur = cur.next;
			i++;
		} while (cur.next != header.next);

		return format("[%s]" , join(sep , arr));
	}

	@Override
	public String toString()
	{
		return toString(" , ");
	}
}
