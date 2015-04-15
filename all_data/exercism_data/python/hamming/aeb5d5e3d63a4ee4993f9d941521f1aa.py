def distance(s1,s2):
	if len(s1) == len(s2):
		l1 = list(s1)
		l2 = list(s2)
		count = 0
		for i in range(len(l1)):
			if l1[i] != l2[i]:
				count+=1
		return count
		

