def distance(one,second):

	differences = [1 for i in range(0,len(one)) if one[i] != second[i]]
	return sum(differences)
