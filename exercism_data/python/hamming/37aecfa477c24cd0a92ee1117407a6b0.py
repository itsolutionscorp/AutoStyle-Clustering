def distance(dnaA, dnaB):
	hammingNum=0
	for a,b in zip(dnaA, dnaB):
		if a != b:
			hammingNum+=1
	return hammingNum
