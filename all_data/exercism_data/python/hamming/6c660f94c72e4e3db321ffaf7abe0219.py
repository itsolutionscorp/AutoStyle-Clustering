def distance(preStrand, strand):
	count=0
	for i in range(len(strand)):
		if preStrand[i]!=strand[i]:
			count+=1
	return count
