class Matrix(object):
	def __init__(self, matrix):
		self.rows = self.mkrows(matrix)
		self.columns = self.mkcolumns(matrix)

	def mkrows(self, matrix):
		rows = matrix.split('\n')
		listrows = []
		for row in rows:
			row = row.strip()
			listrows.append(row.split(' '))
		newlistrows = [map(int, x) for x in listrows]
		return newlistrows
		

	def mkcolumns(self, matrix):
		listrows = self.mkrows(matrix)
		columns = zip(*listrows)
		columns = [list(a) for a in columns]
		return columns
