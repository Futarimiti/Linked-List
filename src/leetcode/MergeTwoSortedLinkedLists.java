/**
 * https://leetcode.com/problems/merge-two-sorted-lists/
 * submission result shows very inefficient memory use.
 */
public class MergeTwoSortedLinkedLists
{
	public static ListNode mergeTwoLists(ListNode list1 , ListNode list2)
	{
		// define two header nodes in case of empty list (null)
		ListNode header1 = new ListNode(0 , list1);
		ListNode header2 = new ListNode(0 , list2);

		// define two iterators, one each list
		ListNode cur1 = header1;
		ListNode cur2 = header2;

		// the merged list starts here
		// in the end returns the next node of this header
		ListNode mergedListHeader = new ListNode(0 , null);
		ListNode curMerged = mergedListHeader;

		while (true)
		{
			if (cur1.next != null)
			{
				if (cur2.next != null)
				{
					// compare two vals
					if (cur1.next.val < cur2.next.val)
					{
						curMerged.next = new ListNode(cur1.next.val , null);
						cur1 = cur1.next;
						curMerged = curMerged.next;
					} else
					{
						curMerged.next = new ListNode(cur2.next.val , null);
						cur2 = cur2.next;
						curMerged = curMerged.next;
					}
				} else
				{
					curMerged.next = cur1.next;
					return mergedListHeader.next;
				}
			} else
			{
				curMerged.next = cur2.next;
				return mergedListHeader.next;
			}
		}
    }
}
