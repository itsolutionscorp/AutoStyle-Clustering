def distance(str1, str2):
	if len(str1) != len(str2):
		raise ValueError("Strings are not of equal length: %s, %s" % (len(str1), len(str2)))
	dist = 0
	for i in range(len(str1)):
		if str1[i] != str2[i]:
			dist += 1
	return dist
