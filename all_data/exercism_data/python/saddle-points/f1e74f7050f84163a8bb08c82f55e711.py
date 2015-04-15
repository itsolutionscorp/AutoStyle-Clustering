def transpose(matrix):
	'''transpose a list of equal-length lists (i.e., a matrix)'''
	return list(zip(*matrix))

def saddle_points(matrix):
	'''
	Find all saddle points in a matrix.

	A matrix entry is a "saddle point" if it is greater than or equal to
	every element in its row and less than or equal to every element in
	its column.
	'''
	nrows = len(matrix)
	if nrows==0:
		return set()
	ncols = len(matrix[0])
	if any([len(row)!=ncols for row in matrix]):
		raise ValueError("that's not a matrix!")
	saddles = {(jj,ii) for jj in range(nrows) for ii in range(ncols)
			if matrix[jj][ii]==max(matrix[jj]) and
				matrix[jj][ii]==min(transpose(matrix)[ii]) }
	return saddles

if __name__ == '__main__':
        print(saddle_points([[9, 8, 7], [5, 3, 2], [6, 6, 7]]))
        print(saddle_points([[5, 3, 5, 4], [6, 4, 7, 3], [5, 1, 5, 3]]))
        print(saddle_points([[2, 1], [1, 2]]))
        print(saddle_points([]))
        print(saddle_points([[3, 2, 1], [0, 1], [2, 1, 0]]))
