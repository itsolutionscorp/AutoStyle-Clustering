class Matrix (object):
	
	def __init__(self, num_string):
		self.rows = [[int(n) for n in l.split()] 
		             for l in num_string.split('\n')]
		self.columns = [[self.rows[j][i] for j in range(len(self.rows))] 
		                for i in range(len(self.rows[0]))]
