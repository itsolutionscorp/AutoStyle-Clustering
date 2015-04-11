class Matrix(object):
	def __init__(self, matrix):
		matrix = [list(map(int, line.split())) for line in matrix.split('\n')]
		self.rows = matrix
		self.columns = [[matrix[i][j] for i in range(len(matrix))] for j in range(len(matrix[-1]))]
