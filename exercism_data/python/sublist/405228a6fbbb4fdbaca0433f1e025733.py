# assumption: in order for list1 to be a sublist of list2, the elements of list1 must appear consecutively in list2

SUBLIST=-1
SUPERLIST=1
EQUAL=0
UNEQUAL=2

# checks if list1 is a sublist of list2
def _is_sublist_of(list1, list2):
	if len(list1)==0:
		return True
	i=0
	while i<len(list2)-len(list1):
		try:
			i=list2.index(list1[0],i)
		except ValueError:
			return False
		if list2[i:i+len(list1)]==list1:
			return True
		else:
			i+=1
	return False

# checks the sub/super-list relationship between list1 and list2
def check_lists(list1, list2):
	if list1==list2:
		return EQUAL
	elif _is_sublist_of(list1,list2):
		return SUBLIST
	elif _is_sublist_of(list2,list1):
		return SUPERLIST
	return UNEQUAL
