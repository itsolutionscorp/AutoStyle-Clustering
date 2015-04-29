SUBLIST = 'sublist'
SUPERLIST = 'superlist'
EQUAL = 'equal'
UNEQUAL = 'unequal'
def check_lists(a, b):
	if a == b: return 'equal'
	for x in a:
		if not x in b: #if one of a is not in b
			break
	else:
		return 'sublist'
	for x in b:
		if not x in a:
			return 'unequal'
	else:
		return 'superlist'
