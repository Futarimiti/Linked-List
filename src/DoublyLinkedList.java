import static java.lang.String.format;

@SuppressWarnings("unused")
public class DoublyLinkedList<T>
{
	private static class Node<E>
	{
		E data;
		Node<E> prev;
		Node<E> next;

		Node(E data , Node<E> prev , Node<E> next)
		{
			this.data = data;
			this.prev = prev;
			this.next = next;
		}
	}

	final Node<T> header = new Node<>(null , null , null);
	int size = 0;

	private Node<T> lastNode = header; // initially

	@SuppressWarnings("unchecked")
	public static <E> DoublyLinkedList<E> listOf(E... elems)
	{
		DoublyLinkedList<E> res = new DoublyLinkedList<>();
		for (E elem : elems)
		{
			res.append(elem);
		}

		return res;
	}

	public void append(T elem)
	{
		Node<T> newNode = new Node<>(elem , lastNode , null);
		this.lastNode.next = newNode;
		lastNode = newNode;
		size++;
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

		// find target node, its prev and next.
		Node<T> target;

		// find from $header or $lastNode? depends on pos
		if (pos > (this.size -  2) / 2)
		{
			// find from foot.
			target = lastNode;
			for (int i = size - 1 ; i > pos ; i--)
			{
				target = target.prev;
			}
		} else // find from head.
		{
			target = header.next;
			for (int i = 0 ; i < pos ; i++)
			{
				target = target.next;
			}
		}

		if (target.next == null)
		{
			lastNode = target.prev;
			target.prev.next = null;
		} else
		{
			target.prev.next = target.next;
			target.next.prev = target.prev;
		}

		size--;
		System.gc();
	}

	public void insert(T elem , int pos)
	{
		if (pos < 0 || pos > this.size)
		{
			String msg = format("Index: %d, Size: %d" , pos , this.size);
			throw new IndexOutOfBoundsException(msg);
		}

		// find $next of the target
		Node<T> prev;

		// find from $header or $lastNode? depends on pos
		if (pos > (this.size - 2) / 2)
		{
			// find from foot.
			prev = lastNode;
			for (int i = size - 1 ; i >= pos ; i--)
			{
				prev = prev.prev;
			}
		} else
		{
			prev = header;
			for (int i = 0 ; i < pos ; i++)
			{
				prev = prev.next;
			}
		}

		Node<T> newNode = new Node<>(elem , prev , prev.next);
		prev.next = newNode;

		// if prev is last, move $lastNode
		if (newNode.next != null) newNode.next.prev = newNode;
		else this.lastNode = newNode;
		size++;
	}

	public String toString(String sep)
	{
		Node<T> cur = this.header.next;
		String[] elems = new String[this.size];
		for (int i = 0 ; i < elems.length ; i++)
		{
			elems[i] = cur.data.toString();
			cur = cur.next;
		}
		return format("[%s]" , String.join(sep , elems));
	}

	@Override
	public String toString()
	{
		return this.toString(" , ");
	}
}
