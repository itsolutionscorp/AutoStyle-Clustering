SUBLIST, SUPERLIST, EQUAL, UNEQUAL = None, None, None, None

def check_lists(l1, l2):
	global SUBLIST, SUPERLIST, EQUAL, UNEQUAL
	SUBLIST, SUPERLIST, EQUAL, NOEQUAL = None, None, None, None
	l1, l2 = sorted(l1), sorted(l2)

	if len(l1) > len(l2):
		check_lists(l2, l1)
		if SUBLIST:
			SUPERLIST = True
			SUBLIST = None
			return
	
	if len(l1) < len(l2):
		for n in range(len(l2)):
			try:
				del l1[ l1.index(l2[n]) ]
			except ValueError: 
				pass
			if len(l1) == 0:
				SUBLIST = True
				return

	for n in range(len(l1)):
		if l1[n] != l2[n]:
			UNEQUAL = True
			return
	
	EQUAL = True
