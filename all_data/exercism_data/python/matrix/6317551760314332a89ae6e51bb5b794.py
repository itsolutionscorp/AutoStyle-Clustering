class Matrix(object):
	def __init__(self,matrix):
		self.matrix = matrix
		self.rows = []
		self.columns = []
		
		for r in matrix.split('\n'):
			self.rows.append(map(lambda x:int(x),r.strip().split(' ')))

		for i in range(len(self.rows[0])):
			column = []
			for j in range(len(self.rows)):
				column.append(self.rows[j][i])
			self.columns.append(column)
