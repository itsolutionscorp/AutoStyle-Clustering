class Matrix(object):
	def __init__(self, input):
		self.input = input
		self.make_rows()
		self.make_columns()
		
	def make_rows(self):
		self.rows = []
		row = self.input.split('\n')
		for line in row:
			numbers = line.split()
			self.rows.append([int(x) for x in numbers])
	
	def make_columns(self):
		self.columns = []
		for i in range(len(self.rows[0])):
			self.columns.append([row[i] for row in self.rows])
			
