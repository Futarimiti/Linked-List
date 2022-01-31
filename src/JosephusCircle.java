import static java.lang.String.format;
import static java.lang.String.join;

public class JosephusCircle
{
	private static class Person
	{
		final int num;
		Person next;

		public Person(int num , Person next)
		{
			this.num = num;
			this.next = next;
		}

		@Override
		public String toString()
		{
			return format("(%d)" , this.num);
		}
	}

	private final Person header = new Person(-1 , null);
	final Person start; // 1 based!!
	final int skip;
	int remain;

	public JosephusCircle(int peopleNo , int start , int skip)
	{
		if (peopleNo >= 1)
		{
			this.remain = peopleNo;

			Person firstPerson = new Person(1 , null);
			firstPerson.next = firstPerson; // self loop

			this.header.next = firstPerson;
			Person lastPerson = firstPerson;

			for (int i = 2 ; i <= peopleNo ; i++)
			{
				Person newPerson = new Person(i , firstPerson);
				lastPerson.next = newPerson;
				lastPerson = newPerson;
			}
		} else throw new IllegalArgumentException("a Josephus circle should contain at least 1 person");

		if (start >= 1 || start <= peopleNo)
		{
			// shift exactly `start` times from header
			Person startFrom = header;
			for (int i = 1 ; i <= start ; i++)
			{
				startFrom = startFrom.next;
			}
			this.start = startFrom;
		}
		else throw new IllegalArgumentException("`start` must be in 1..`peopleNo`");

		if (skip >= 0) this.skip = skip;
		else throw new IllegalArgumentException("`skip` must be >= 0");
	}

	public SinglyLinkedList<Person> sequence()
	{
		SinglyLinkedList<Person> sequence = new SinglyLinkedList<>();

		Person cur = start; // initially

		while (this.remain > 1)
		{
			Person execPrev = cur;
			// shift (skip) times to the prev of exec
			for (int i = 1 ; i <= skip ; i++)
			{
				execPrev = execPrev.next;
			}

			// execute (remove node)
			sequence.append(execPrev.next);

			// if the executed person is pointed by header,
			// shift header.next to next one
			if (execPrev.next == header.next) header.next = header.next.next;
			execPrev.next = execPrev.next.next;
			remain--;

			cur = execPrev.next;
		}

		// the final remaining one is the survivor.
		System.out.printf("survivor: %d\n" , header.next.num);
		return sequence;
	}

	public String toString(String sep)
	{
		String[] arr = new String[remain];
		Person cur = header;

		int i = 0;
		do
		{
			arr[i] = String.valueOf(cur.next.num);
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
