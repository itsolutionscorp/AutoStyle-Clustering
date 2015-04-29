SUBLIST = "SUBLIST"
SUPERLIST = "SUPERLIST"
EQUAL = "EQUAL"
UNEQUAL = "UNEQUAL"

def check_lists(list1, list2):
	list1, list2 = sorted(list1), sorted(list2)
	if list1 == list2:
		return EQUAL
	subsup = 0 if len(list1) < len(list2) else 1
	if subsup:
		list1, list2 = list2, list1
	while list1:
		try:
			list2.pop(list2.index(list1.pop()))
		except ValueError:
			return UNEQUAL
	return SUPERLIST if subsup else SUBLIST
