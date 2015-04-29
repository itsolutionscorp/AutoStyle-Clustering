def saddle_points(matrix):
	saddle_points = set()
	if not matrix:
		return saddle_points
	width = len(matrix[-1])
	height = len(matrix)
	if not all(len(line) == width for line in matrix):
		raise ValueError

	for i in range(height):
		largest = max(matrix[i])
		for j in range(width):
			smallest = min(matrix[k][j] for k in range(height))
			if matrix[i][j] == largest and largest == smallest:
				saddle_points.add((i, j))

	return saddle_points
