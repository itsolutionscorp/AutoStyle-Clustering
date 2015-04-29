def distance(strandA, strandB):

	Alen = len(strandA)
	Blen = len(strandB)
	hamdist = 0

	if Alen == Blen:
		for char in range(Alen):
			if strandA[char] != strandB[char]:
				hamdist+=1
		return hamdist
