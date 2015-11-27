def num_common_letters(a,b):
	s = list()
	blist = list()
	la = len(a)
	lb = len(b)
	while not (la==0 and lb==0):
		if la==0:
			if(b[0] in s and b[0] not in blist):
				blist+=b[0]
			b = b[1:]
			lb = len(b)
		else:
			if(a[0] not in s):
				s+=a[0]
			a = a[1:]
			la = len(a)
	return len(blist)

Positive Hints


Negative Hints


Approach Hints
Consider this approach: for each letter in the first word, think about how can you find whether it is also in the second word. To find whether it is also in the second word, you should only have to use a single line of code. Use the skeleton we've provided if you're stuck.

Skeleton
def num_common_letters(---------, -----):
    --------- = -
    --------- = []
    for - in list(---------):
        if - in list(-----) --- - --- in ---------:
            --------- += - 
            --------- += -
    return ---------    
