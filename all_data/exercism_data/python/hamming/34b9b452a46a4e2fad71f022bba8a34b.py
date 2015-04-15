def distance(string1, string2):
	counter = 0
	for i in range(len(string1)):
		if string1[i] != string2[i]:
			counter += 1
	return counter
