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
		
		
def largest_product(folge,num):
	if num <1:
		return 1
	matrix = slices(folge,num)
	maximum = 0
	for i in matrix:
		product = prod(i)
		if product > maximum:
			maximum = product
	return maximum
	
def prod(listen):
	k = 1
	for i in listen:
		k *=i
	return k
		
		
