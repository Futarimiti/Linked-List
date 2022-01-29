import java.util.LinkedList;
import java.util.List;

public class ListNode
{
	int val;
	ListNode next;

	ListNode(int val , ListNode next)
	{
		this.val = val;
		this.next = next;
	}

	// for use in Main.java only
	protected static ListNode listOf(int... vals)
	{
		ListNode header = new ListNode(0 , null);
		ListNode curr = header;

		for (int i = 0 ; i < vals.length ; i++)
		{
			curr.next = new ListNode(vals[i] , null);
			curr = curr.next;
		}

		return header.next;
	}

	// for use in Main.java only
	@Override
	public String toString()
	{
		List<String> elems = new LinkedList<>();

		ListNode cur = new ListNode(0 , this);
		while (cur.next != null)
		{
			elems.add(String.valueOf(cur.next.val));
			cur = cur.next;
		}

		String[] arr = new String[elems.size()];
		elems.toArray(arr);

		return String.format("[%s]" , String.join(" , " , arr));
	}
}
