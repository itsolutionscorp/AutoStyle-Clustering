class Matrix:
	def __init__(self, matrix):
		self.matrix = matrix
		self.rows = [
                [int(x) for x in row.split(' ')]
                for row in matrix.split('\n')
                ]
       		self.columns = [list(i) for i in zip(*self.rows)]
