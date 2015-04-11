SUBLIST = 'SUBLIST'
SUPERLIST = 'SUPERLIST'
EQUAL = 'EQUAL'
UNEQUAL = 'UNEQUAL'

def check_lists(list1, list2):
	if len(list1) == len(list2):
		if contains(list1, list2):
			return EQUAL
	elif len(list2) > len(list1):
		if contains(list1, list2):
			return SUBLIST
	else:
		if contains(list2, list1):
			return SUPERLIST
	
	return UNEQUAL

def contains(sublist, superlist):
	for i in xrange(len(superlist) - len(sublist) + 1):
		for j in xrange(len(sublist)):
			if superlist[i+j] != sublist[j]:
				break
		else:
			return True

	return False
