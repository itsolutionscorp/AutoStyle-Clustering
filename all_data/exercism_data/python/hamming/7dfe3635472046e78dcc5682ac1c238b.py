def distance(str1, str2):
	hammingNum=0
	for i, letter in enumerate(str1):
		if letter != str2[i]:
			hammingNum+=1

	return hammingNum
