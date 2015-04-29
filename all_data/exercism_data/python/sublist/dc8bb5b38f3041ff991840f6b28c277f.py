SUBLIST, SUPERLIST, EQUAL, UNEQUAL = range(4)

def check_lists(l1, l2):
	if len(l1)>len(l2):
		if check(l1,l2):
			return SUPERLIST
	elif len(l2)>len(l1):
		if check(l2,l1):
			return SUBLIST
	elif l1 == l2:
		return EQUAL
	return UNEQUAL

def check(a1, a2):
	for i in range(len(a1) + 1 - len(a2)):
		if a1[i:i + len(a2)] == a2:
			return True
	return False
