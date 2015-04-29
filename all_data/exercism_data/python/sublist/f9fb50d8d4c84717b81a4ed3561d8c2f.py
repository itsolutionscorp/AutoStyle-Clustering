SUBLIST='sublist'
SUPERLIST='superlist'
EQUAL='equal'
UNEQUAL='unequal'

	
def check_lists(first, second):
	first.sort()
	second.sort()
	if len(first) == len(second):
		if first == second:
			return 'equal'
		return 'unequal'
	if len(first)>len(second):
		return checker(second, first, True)
	return checker(first, second)
	
	
def checker(one, two, swap=False):
	one_hist = {x:one.count(x) for x in one}
	for item in one_hist:
		if item not in two or one_hist[item]>two.count(item):
			return 'unequal'
	if swap:
		return 'superlist'
	return 'sublist'
