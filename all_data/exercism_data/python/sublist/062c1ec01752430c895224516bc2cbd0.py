EQUAL = 3
UNEQUAL = 0
SUBLIST = 1
SUPERLIST = 2

def sub_list(list1, list2):
	if not list1:
		return True
	while list1[0] in list2:
		i = list2.index(list1[0])
		if list2[i:i+len(list1)] == list1:
			return True
		list2 = list2[i+1:]	
	return False
	
def check_lists(list1, list2):
	sub = sub_list(list1, list2)
	sup = sub_list(list2, list1)
	return sub + 2*sup
