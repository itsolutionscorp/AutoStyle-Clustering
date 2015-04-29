def rows_to_columns(matrix):
	return [[row[n] for row in matrix] for n in range(len(matrix[0]))]

def max_in_row(matrix, rowNumber):
	return max(matrix[rowNumber])

def min_in_column(matrix, colNumber):
	return min(rows_to_columns(matrix)[colNumber])
	
def saddle_points(matrix):
	if len(matrix) == 0:
		return set() #Empty matrix
		
	firstRowLength = len(matrix[0])
	
	if firstRowLength == 0:
		return set() #Empty matrix and or broken row
	
	for row in matrix[1:]:
		if len(row) != firstRowLength:
			raise ValueError('irregular matrix')
			
	saddlePoints = []
	
	for y, row in enumerate(matrix):
		for x, n in enumerate(row):
			if max_in_row(matrix, y) == n and min_in_column(matrix, x) == n:
				saddlePoints.append((y, x))
				
	return set(saddlePoints)
