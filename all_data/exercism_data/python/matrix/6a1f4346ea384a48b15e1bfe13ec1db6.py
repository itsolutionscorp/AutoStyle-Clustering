class Matrix():
	def __init__(self, matrixString):
		self.rows = []
		self.columns = []
		rowSplits = matrixString.split('\n')
		
		for index, individualRow in enumerate(rowSplits):
			self.rows.append(individualRow.split(' '))
			self.rows[index] = map(int, self.rows[index])

		for column in range (0, len(self.rows[0])):
			self.columns.append([row[column] for row in self.rows])
