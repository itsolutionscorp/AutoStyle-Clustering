EQUAL='EQUAL'
UNEQUAL='UNEQUAL'
SUBLIST='SUBLIST'
SUPERLIST='SUPERLIST'

def check_lists(l1,l2):
	sub=True
	sup=True
	for a in l1:
		if a not in l2:
			sub=False
			break
	for a in l2:
		if a not in l1:
			sup=False
			break
	if sub and sup:
		return EQUAL
	if sub:
		return SUBLIST
	if sup:
		return SUPERLIST
	return UNEQUAL
	
if __name__ == '__main__':
	print check_lists([0, 1, 2],[0, 1, 2])
