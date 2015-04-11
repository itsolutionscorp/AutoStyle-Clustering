def distance(str1, str2):
	distance_value = 0
	for i in range(len(str1)):
		if str1[i] != str2[i]:
			distance_value += 1
	return distance_value
