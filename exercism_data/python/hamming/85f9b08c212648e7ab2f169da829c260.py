def distance(DNA_1, DNA_2):
	distance = 0
	for i in list(DNA_1):
		#print i
		if i in list(DNA_2):
			#print j
			pass
		else:
			distance += 1
	return distance		
