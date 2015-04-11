def distance(str1, str2):
	if not len(str1) == len(str2):
		return "INVALID"

	l1 = list(str1)
	l2 = list(str2)
	count = 0

	for i, val in enumerate(l1):
		if not l1[i] == l2[i]:
			count += 1

	return count
