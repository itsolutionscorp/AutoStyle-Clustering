def distance(stranda,strandb):
	l=len(stranda)
	dist=0
	for i in xrange(l):
		if (stranda[i] != strandb[i]):
			dist+=1
	return dist	
