import itertools

def saddle_points(matrix):
	# check that matrix is valid
	if not len(set(len(row) for row in matrix)) <= 1:
		raise ValueError('Not a valid matrix.')

	# find saddle points
	maximums = map(max, matrix)
	minimums = map(min, itertools.izip(*matrix))
	return set(
		(i, j) for i, row in enumerate(matrix) for j, item in enumerate(row)
		if item == maximums[i] and item == minimums[j]
	)
