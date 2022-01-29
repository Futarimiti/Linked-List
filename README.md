# Linked List
A linked list is a linear collection of data elements whose order is not given by their physical placement in memory; instead, each element points to the next.

A linked list consists of a collection of nodes which together represent a sequence. In its most basic form, each node contains data and a reference pointing to the next node in the sequence.

Linked lists have developed multiple versions depending on different demands: one can have a head node or headless, can be unidirectional (singly) or bidirectional (doubly), can be circular or open... etc.

## Singly Linked List
A singly linked list should have a head node storing reference pointing to the first element, and subsequently all the others.

### Code implementation
First define a node class. This could be done in a separate file, and here we'll define it as an inner class.</br>
A node should include data and reference to next element:
```java
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
```

The header node does not store any information but the address of the first element. So we have `header` defined as:
```java
Node<T> header = new Node<>(null , null);
```
Where `next` of `header` will be pointing to the first element added in.

Now we'll define `append` method to append an element to the end of the list. When appending the element, we should change `next` of the last node to the new element added:
```java
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
```
Where `size` gives the number of elements in the current linked list:
```java
private int size = 0;
```

...and method `size()` returns `size`:
```java
public int size()
{
	return size;
}
```

We do not have a maximum size for this linked list; if we want, we can define a max size and change method return type to boolean.

Next we'll define method `insert` to insert an element **before** the nth element. To do this, we'll change the last element's `next` to this element, with `next` of this element pointing to the next element:
```java
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
```
<!-- Where method `size` returns number of elements in the linked list:
```java
public int size()
{
	int counter = 0;
	Node<T> iterator = header;
	while (iterator.next != null)
	{
		counter++;
		iterator = iterator.next;
	}

	return counter;
}
``` -->

Lastly, we'll define method `remove` to remove the nth element:
```java
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
```

The linked list herein has the ability to add and delete elements. We'll now focus on methods on individual elements.

Method `set` to update content of the nth element:
```java
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
```

Method `get` to get content of the nth element:
```java
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
```
