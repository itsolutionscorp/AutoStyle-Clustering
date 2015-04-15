SUBLIST, SUPERLIST, EQUAL, UNEQUAL = 0, 1, 2, 3

def check_lists(l1, l2):
	if len(l1) == len(l2):
		if l1 == l2:
			return EQUAL
	
	elif len(l1) < len(l2):
		for i in range( len(l2) - len(l1) + 1 ):
			if l1 == l2[i:i+len(l1)]:
				return SUBLIST

	elif len(l1) > len(l2):
		if SUBLIST == check_lists(l2, l1):
			return SUPERLIST
	return UNEQUAL
