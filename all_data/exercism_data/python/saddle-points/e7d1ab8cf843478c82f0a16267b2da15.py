def scanrow(matrix, ix):
	row = matrix[ix]
	max_v = row[0]
	points = [ tuple([ ix, 0 ]) ]
	for iy in range(1, len(row)):
		if row[iy] > max_v:
			max_v = row[iy]
			points = [ tuple([ ix, iy ]) ]
		elif row[iy] == max_v:
			points.append(tuple([ ix, iy ]))
	return points

def scancol(matrix, point):
	pix = point[0]
	piy = point[1]
	p_val = matrix[pix][piy]

	for ix in range(0, len(matrix)):
		if ix == pix:
			continue
		if matrix[ix][piy] < p_val:
			return False
	return True

def saddle_points(matrix):
	out = set()
	if len(matrix) == 0:
		return out
	row_len = len(matrix[0])
	for ix in range(0, len(matrix)):
		if row_len != len(matrix[ix]):
			raise ValueError('irregular matrix')
	for ix in range(0, len(matrix)):
		row_set = scanrow(matrix, ix)
		for point in row_set:
			if scancol(matrix, point):
				out.add(point)
	return out
