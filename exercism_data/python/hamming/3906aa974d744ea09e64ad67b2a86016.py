def distance(DNA1,DNA2):
	distance=0
	for i,letter in enumerate(DNA1):
		if letter!= DNA2[i]:
			distance+=1
	return distance
