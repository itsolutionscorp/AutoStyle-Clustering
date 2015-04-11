def saddle_points(matrix):
	if len(set(map(len, matrix))) > 1:
		raise ValueError("Matrix must be irregular to calculate saddle points")

	candidates_by_row = _candidates(matrix, max)
	candidates_by_col = map(lambda(a,b):(b,a), _candidates(zip(*matrix), min))
	return set(candidates_by_row).intersection(candidates_by_col)

def _candidates(matrix, value_by_row):
	""" Yield coordinates of all items equal to `value_by_row(row_of_item)`
	"""
	for y, row in enumerate(matrix):
		value = value_by_row(row)
		for x, item in enumerate(row):
			if item == value:
				yield (y, x)
