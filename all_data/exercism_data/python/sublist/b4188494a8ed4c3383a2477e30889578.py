SUBLIST
EQUAL
UNEQUAL
SUPERLIST

def check_lists(l1, l2):
	if l1 == l2:
		return EQUAL
	elif False not in [x in l1 for x in l2]:
		return SUPERLIST
	elif False not in [y in l2 for y in l1]:
		return SUBLIST
	else:
		return UNEQUAL
