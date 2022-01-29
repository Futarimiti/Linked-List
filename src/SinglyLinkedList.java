import static java.lang.String.format;

public class SinglyLinkedList<T>
{
	private static class Node<E>
	{
		E data = null;
		Node<E> next = null;

		Node(E data , Node<E> next)
		{
			this.data = data;
			this.next = next;
		}
	}

	Node<T> header = new Node<>(null , null);
	private int size = 0;

	public void append(T elem)
	{
		// find last node from $header.
		Node<T> lastNode = header;
		while (lastNode.next != null)
		{
			lastNode = lastNode.next;
		}

		// define a new node for $elem and append to $lastNode.
		lastNode.next = new Node<>(elem , null);
		size++;
	}

	/**
	 * can be used as `append` but not recommended
	 */
	public void insert(T elem , int pos)
	{
		// filter invalid positions.
		if (pos < 0)
		{
			String msg = format("Index: %d, Size: %d" , pos , this.size());
			throw new IndexOutOfBoundsException(msg);
		}

		// find last node from header.
		Node<T> lastNode = header;

		for (int i = 1 ; i <= pos ; i++)
		{
			lastNode = lastNode.next;
			if (lastNode == null)
			{
				String msg = format("Index: %d, Size: %d" , pos , this.size());
				throw new IndexOutOfBoundsException(msg);
			}
		}

		Node<T> nextNode = lastNode.next; // null ok
		lastNode.next = new Node<>(elem , nextNode);
		size++;
	}

	public void remove(int pos)
	{
		// filter negative positions.
		if (pos < 0)
		{
			String msg = format("Index: %d, Size: %d" , pos , this.size());
			throw new IndexOutOfBoundsException(msg);
		}
		// find prev of the target node.
		Node<T> prev = header;

		for (int i = 0 ; i < pos ; i++)
		{
			prev = prev.next;
			if (prev == null)
			{
				String msg = format("Index: %d, Size: %d" , pos , this.size());
				throw new IndexOutOfBoundsException(msg);
			}
		}

		Node<T> target = prev.next;
		if (target == null)
		{
			String msg = format("Index: %d, Size: %d" , pos , this.size());
			throw new IndexOutOfBoundsException(msg);
		}
		prev.next = target.next;
		size--;
		System.gc();
	}

	public void set(int pos , T update)
	{
		// find target node.
		Node<T> target = header;

		for (int i = 0 ; i <= pos ; i++)
		{
			target = target.next;
			if (target == null)
			{
				String msg = format("Index: %d, Size: %d" , pos , this.size());
				throw new IndexOutOfBoundsException(msg);
			}
		}

		target.data = update;
	}

	public T get(int pos)
	{
		// find target node.
		Node<T> target = header;

		for (int i = 0 ; i <= pos ; i++)
		{
			target = target.next;
			if (target == null)
			{
				String msg = format("Index: %d, Size: %d" , pos , this.size());
				throw new IndexOutOfBoundsException(msg);
			}
		}

		return target.data;
	}

	public int size()
	{
		return size;
	}
}
