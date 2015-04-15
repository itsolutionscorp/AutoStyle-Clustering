import re

SUBLIST = "sublist"
SUPERLIST = "superlist"
EQUAL = "equal"
UNEQUAL = "unequal"

def check_lists(a, b):
	a = "".join(str(e) for e in a)
	b = "".join(str(e) for e in b)
	result = 0
	if b.find(a) > -1:
		result += 1
	if a.find(b) > -1:
		result += 2
	if result == 0:
		return(UNEQUAL)
	if result == 1:
		return(SUBLIST)
	if result == 2:
		return(SUPERLIST)
	if result == 3:
		return(EQUAL)
	return(UNEQUAL)
