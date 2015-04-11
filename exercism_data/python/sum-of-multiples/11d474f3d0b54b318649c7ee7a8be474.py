def sum_of_multiples(*args):
	mult = [3,5]
	if len(args) == 2:
		mult = args[1]
	return sum([x for x in range(0,args[0]) if checkMult(x,mult)])
		
def checkMult(n,m):
	return any([n%x==0 for x in m if x > 0])
