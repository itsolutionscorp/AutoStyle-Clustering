##matrix


class Matrix(object):
	"""docstring for Matrix"""
	

	def __init__(self, string):
		self.rows = [ 
		[int(num) for num in row.split(' ')]
		for row in string.split('\n')
		]

		self.columns = [list(tup) for tup in zip(*self.rows)]
