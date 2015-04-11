def distance(first, second):
	count = 0
	if ( len(first) == len(second) ):
		i = 0
		while (i < len(first)):
			if (first[i] != second[i]):
				count = count +1
			i = i + 1
		return count
	else:
		print("The parameters do not have the same length !")
		return -1
