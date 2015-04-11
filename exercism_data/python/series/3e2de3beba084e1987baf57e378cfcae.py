def slices(folge,num):
	matrix = []
	if num > len(folge) or num <1:
		raise ValueError()
	else:
		for i in range(0,1 + len(folge)-num):
			submatrix =[]
			for k in range(0,num):
				submatrix.append(int(folge[i + k]))
			matrix.append(submatrix)
		return matrix
