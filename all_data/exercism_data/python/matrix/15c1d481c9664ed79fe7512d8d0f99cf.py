class Matrix(object):
	rows = []
	columns = []

	def __init__(self, matrix):
		self.convertStringInMatrix(matrix)
		
		self.transposeMatrix()

	def convertStringInMatrix(self, string):
		temp_rows = string.split("\n")
		for i in temp_rows:
			temp_row = [int(x) for x in i.split(" ")]
			self.rows.append(temp_row)
		
	def transposeMatrix(self):
		for i in range(0, len(self.rows[0])):
			temp_columns = []
			for j in range(0, len(self.rows)):
				temp_columns += [self.rows[j][i]]
			self.columns.append(temp_columns)
