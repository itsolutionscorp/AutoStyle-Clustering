def saddle_points(inp):
	'''
	A "saddle point" is greater than or equal to
	every element in its row and the less than or equal to every element in
	its column.

	'''
	sad_poi = set()
	if len(inp) == 0: return sad_poi
	if sum(len(i) for i in inp) != len(inp)*len(inp[0]):
		raise ValueError("Matrix must not be irregular")
	for i in range(len(inp)):
		for j in range(len(inp[0])):
			testSP = inp[i][j]
			row = inp[i]
			col = [m[j] for m in inp]
			if testSP == max(row) and testSP == min(col):
				sad_poi.add((i,j))
	return sad_poi
