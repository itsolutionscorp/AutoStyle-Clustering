SUBLIST = 0b01
SUPERLIST = 0b10
EQUAL = 0b11
UNEQUAL = 0b00

def check_lists(l1, l2):
	l1, l2 = set(l1), set(l2)
	retval = 0
	if l1 >= l2:
		retval |= SUPERLIST
	if l1 <= l2:
		retval |= SUBLIST
	return retval
