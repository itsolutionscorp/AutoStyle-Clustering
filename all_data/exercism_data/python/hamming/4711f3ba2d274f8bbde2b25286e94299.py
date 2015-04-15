def distance(str1, str2):
	return sum([1 for (x,y) in zip(str1,str2) if x != y])
