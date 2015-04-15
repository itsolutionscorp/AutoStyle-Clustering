def distance(s1,s2):
	hamming=0
	for i in range(len(s1)):
		if s1[i]!=s2[i]:
			hamming+=1
	return hamming
