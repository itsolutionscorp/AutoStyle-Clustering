class Matrix(object):

	def __init__(self, input):
		self.rows = []
		self.columns = []
		self._lines = input.splitlines()

		self._set_rows()
		self._set_columns()


	def _set_rows(self):
		for line in self._lines:
			row = [ int(x) for x in line.split()]
			self.rows.append(row)


	def _set_columns(self):
		zipped_tupple = zip(*self.rows)
		self.columns = [list(x) for x in zipped_tupple]


	
			
