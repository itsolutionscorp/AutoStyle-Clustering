def distance(strand1,strand2):
	s1=list(strand1)
	s2=list(strand2)
	distance=0
	if(len(s1)==len(s2)):
		for i in range(len(s1)):
			if(s1[i]!=s2[i]):
				distance+=1
	return distance
