class Matrix(object):
	'''
		Constructor to run methods
	'''
	def __init__(self, str_data):
		self.rows = self.parse(str_data)
		self.columns = self.get_cols()

	'''
		Parse raw string and organize it in a matrix form
	'''
	def parse(self, data):
		return [map(int, el.split()) for el in data.split('\n')]

	'''
		Extract and organize column data
	'''
	def get_cols(self):
		cols = []
		for jdx in xrange(len(self.rows[0])):
			cols.append([row[jdx] for row in self.rows])
		return cols
