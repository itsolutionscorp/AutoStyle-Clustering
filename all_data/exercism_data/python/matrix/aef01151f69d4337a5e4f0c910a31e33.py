class Matrix(object):
	def __init__(self, matrixString):
		# Rows
		# Take input string, split by \n to get each line
		# Split by space to get each character
		self.rows = [[int(character) for character in row.split()] for row in matrixString.split("\n")]
		# Columns
		# Take the value from each column in each row to create a new row
		self.columns = [[row[colNo] for row in self.rows] for colNo in range(0, len(self.rows)-1)]
