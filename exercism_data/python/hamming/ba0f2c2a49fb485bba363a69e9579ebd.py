def distance(A,B):
	splitA = list(A)
	splitB = list(B)
	test = [splitA[x] != splitB[x] for x in range(len(splitA))]
	counttrue = len([x for x in test if x])
	return counttrue
