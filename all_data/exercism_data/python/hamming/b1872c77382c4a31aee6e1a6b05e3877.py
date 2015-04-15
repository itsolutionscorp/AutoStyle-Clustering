def distance(str1,str2):
	"""returns hamming score for two strings"""
	score=0
	for i in range(len(str1)):
		if str1[i] != str2[i]:
			score+=1
	return score
