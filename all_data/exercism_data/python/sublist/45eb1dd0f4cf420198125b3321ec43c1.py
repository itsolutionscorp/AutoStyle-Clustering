SUBLIST =-1
EQUAL=0
SUPERLIST= 1

UNEQUAL=2


def check_lists(one,two):
	if len(one)==len(two):
		if one == two:
			return 0
		else:
			return 2
	elif len(one)>len(two):
		if sup(one,two):
			return 1
		else:
			return 2
	elif len(one)<len(two):
		if sup(two,one):
			return -1
		else:
			return 2
			
def sup(one,two):
	for i in range(0,(len(one)-len(two)+1)):
		if two==one[i:(i+len(two))]:
			return True
	
