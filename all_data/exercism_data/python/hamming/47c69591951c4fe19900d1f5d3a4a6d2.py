def distance(SEQ1,SEQ2):
	score = 0
	for i in range(len(SEQ1)):
		if SEQ1[i] != SEQ2[i]: score +=1
	return score
	
