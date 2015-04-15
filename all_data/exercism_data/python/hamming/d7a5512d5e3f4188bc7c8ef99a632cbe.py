def distance(st1,st2):
	counter = 0
	if len(st1) == len(st2):
		for i in range(len(st1)):
			if st1[i] != st2[i]:
				counter+=1
	return counter
