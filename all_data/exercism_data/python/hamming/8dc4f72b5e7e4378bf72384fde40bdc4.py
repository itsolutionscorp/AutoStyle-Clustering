def distance(seqa, seqb):
	
	hemd = 0
	for x in range(len(seqa)):
		if not seqa[x] == seqb[x]:
			hemd += 1
	return hemd
