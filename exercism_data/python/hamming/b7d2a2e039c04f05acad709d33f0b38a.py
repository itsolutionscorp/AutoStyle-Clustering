def distance(dna, ham):
	dis=0
	for i in range(0,len(dna)):
		if dna[i]!=ham[i]:
			dis+=1
	return dis
