class Matrix(object):
	def __init__(self,string):
		self._rows = [ [ int(n) for n in line.split(' ') ] for line in string.split('\n') ]

	@property
	def rows(self):
		return self._rows

	@property
	def columns(self):
		return [ [ self._rows[j][i] for j in range(len(self._rows)) ] for i in range(len(self._rows[0])) ]
