class Matrix:
	def __init__(self, matrixString):
		self.rows = self.stringToMatrixRows(matrixString)
		self.columns = self.rowsToColumns(self.rows)
		
	def stringToMatrixRows(self, matrixString):
		return [[int(number) for number in row.strip().split(" ")] for row in matrixString.split("\n")]
	
	def rowsToColumns(self, rows):
		return [[row[n] for row in rows] for n in range(len(rows[0]))]
