def hamming_distance(str1, str2):
	count = 0
	count1 = 0
	for l in str1:
		if l != str2[count1]:
			count += 1
		count1 += 1
	return count
