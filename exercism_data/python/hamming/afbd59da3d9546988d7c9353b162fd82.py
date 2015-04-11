def distance(one, two):
	if len(one) == len(two):
		h_dis = 0
		for num in range(0,len(one)):
			if one[num] != two[num]:
				h_dis +=1
	
	return h_dis
	
