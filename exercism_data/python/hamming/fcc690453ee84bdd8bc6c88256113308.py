def distance(str1, str2):

	distance = 0
	for letter1, letter2 in zip(str1, str2):
		if letter1 != letter2:
			distance += 1

	return distance
