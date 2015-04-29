''' Determine if a list is a sublist or superlist of another '''

UNEQUAL = 0
EQUAL = 1
SUBLIST = 2
SUPERLIST = 3

def is_sublist(shortlist,longlist):
	for ii in range(len(longlist)-len(shortlist)+1):
		allEqual = True
		for jj in range(len(shortlist)):
			if shortlist[jj] != longlist[ii+jj]:
				allEqual = False
				break
		if allEqual:
			return allEqual
	return allEqual

def check_lists(l1,l2):
	if len(l1)<len(l2):
		if is_sublist(l1,l2):
			return SUBLIST
	elif len(l2)<len(l1):
		if is_sublist(l2,l1):
			return SUPERLIST
	else: # len1==len2
		if is_sublist(l2,l1):
			return EQUAL
	return UNEQUAL



if __name__=='__main__':

	l1 = [0, 1, 2, 3]
	l2 = [0, 1, 2]
	l1 = [0, 1, 2, 3, 4, 5]
	l2 = [3, 4, 5]
	print(check_lists(l1,l2))
