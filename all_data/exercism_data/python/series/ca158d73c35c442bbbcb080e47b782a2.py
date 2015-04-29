def slices(seq,n):
	if (n > len(seq)) or (n == 0):
		raise ValueError
	
	var = []
	for i in range(len(seq)):
		temp = []
		for j in range(n):
			if (i+n <= len(seq)):
				temp.append(int(seq[i+j]))
		var.append(temp)
	return [x for x in var if x]
