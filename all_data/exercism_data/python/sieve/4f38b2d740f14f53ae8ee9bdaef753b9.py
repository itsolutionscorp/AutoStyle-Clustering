def sieve(limit):
	Numbers = range(2, limit + 1)
	FinalList = []
	for x in range(2, limit+1):
		if x not in Numbers:
			continue
		FinalList = []
		for n in Numbers:
			if x == n or n%x != 0:
				FinalList.append(n)
		Numbers = FinalList
	return FinalList
