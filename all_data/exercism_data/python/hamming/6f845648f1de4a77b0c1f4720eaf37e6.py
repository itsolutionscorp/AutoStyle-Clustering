def distance(s1,s2):
	count = 0
	i = 0

	if len(s1) == len(s2):
		for i in range(len(s1)):
			if s1[i] != s2[i]:
				count += 1
	else:
		return "Length are not the same"

	return count
