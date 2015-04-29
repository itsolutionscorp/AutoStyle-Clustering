# calculates the set of saddle points in matrix
def saddle_points(matrix):
	if len(matrix)>0:
		for row in matrix:
			if len(row)!=len(matrix[0]):
				raise ValueError("all rows must have the same length.")
	answer=set()
	if len(matrix)==0:
		return answer
	maxes=[ max(row) for row in matrix ]
	mins = [ min([ row[column_num] for row in matrix ]) for column_num in range(len(matrix[0])) ]
	for i in range(len(matrix)):
		for j in range(len(matrix[i])):
			if matrix[i][j]==maxes[i]==mins[j]:
				answer.add((i,j))
	return answer
