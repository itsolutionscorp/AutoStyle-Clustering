def saddle_points(matrix):
	result = []

	for row_pos in range(len(matrix)):
		if any(len(row) != len(matrix[0]) for row in matrix):
				raise ValueError ("irregular matrix")

		max_on_row = max(matrix[row_pos])
		for col_pos in range(len(matrix)):
			if max_on_row == matrix[row_pos][col_pos]:
				result.append((row_pos, col_pos))

	for col_pos in range(len(matrix)):	
		temp = []
		
		for row_pos in range(len(matrix)):
			temp.append(matrix[row_pos][col_pos])
		min_on_col = min(temp)

		for row_pos in range(len(temp)):
			if min_on_col != temp[row_pos] and (row_pos, col_pos) in result:
				result.remove((row_pos, col_pos))

	return set(result)


inp = [[5,3,5,4],[6,4,7,3],[5,1,5,3]]
#ans = set([(0,0),(0,2),(2,0),(2,2)])
print saddle_points(inp)
