def check_lists(list1,list2):
	if set(list1) == set(list2):
		return 'EQUAL'
	elif set.issubset(set(list1),set(list2)):
		return 'SUBSET'
	elif set.issuperset(set(list1),set(list2)):
		return'SUPERSET'
	else:
		return 'UNEQUAL' 


EQUAL='EQUAL'
SUBLIST='SUBSET'
SUPERLIST='SUPERSET'
UNEQUAL='UNEQUAL'
