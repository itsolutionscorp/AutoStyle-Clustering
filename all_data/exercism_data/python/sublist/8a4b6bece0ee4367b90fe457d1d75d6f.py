SUBLIST	= 1
SUPERLIST = 2
EQUAL = 3
UNEQUAL = 4

def check_lists(list1, list2):

	len1 = len(list1)
	len2 = len(list2)

	if len1 == len2:
		if list1 == list2:
			return EQUAL

	if len1 > len2:
		for n in xrange(len1 - len2 + 1):
			if list1[n:n + len2] == list2:
				return SUPERLIST

	if len1 < len2:
		for n in xrange(len2 - len1 + 1):
			if list2[n:n + len1] == list1:
				return SUBLIST

	return UNEQUAL
