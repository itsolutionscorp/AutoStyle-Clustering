class Matrix(object):

	def __init__(self, input):

		rows = [map(int, row.split()) for row in input.split('\n')]

		width = len(rows[0])
		height = len(rows)

		self.rows = rows
		self.columns = [
			[rows[i][j] for i in range(height)]
			for j in range(width)
		]
