SUBLIST = 'sub'
SUPERLIST = 'super'
EQUAL ='eq'
UNEQUAL = 'uneq'

def check_lists(l1, l2):
	if len(l1) == len(l2):
		if l1 == l2:
			return EQUAL
		else:
			return UNEQUAL
	else:
		if len(l1) < len(l2):
			if len(l1) == 0:
				return SUBLIST
			elif l1[0] in l2: 
				if l1 in getSlice(l2,len(l1)):
					return SUBLIST
				else:
					return UNEQUAL
			else:
				return UNEQUAL
		else:
			if len(l2) == 0:
				return SUPERLIST
			elif l2[0] in l1:
				if l2 in getSlice(l1,len(l2)):
					return SUPERLIST
				else:
					return UNEQUAL
			else:
				return UNEQUAL
			

		
def getSlice(l,n):
	r = list()
	for ind,v in enumerate(l):
		if ind + n <= len(l):
			r.append([x for x in l[ind:n+ind]])
	return r
