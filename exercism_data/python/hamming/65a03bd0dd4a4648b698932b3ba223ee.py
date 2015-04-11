def hamming(str1, str2):
	distance = 0
	len1, len2 = len(str1), len(str2)
	for i in range(max(len1, len2)):
		if i > min(len1, len2) -1 or str1[i] != str2[i]:
			distance += 1
	return distance
