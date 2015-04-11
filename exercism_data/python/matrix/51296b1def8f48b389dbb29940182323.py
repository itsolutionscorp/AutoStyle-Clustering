class Matrix():
	'''Return specified row or column of given matrix'''
	def __init__(self,mat_str):
		self.nrows = mat_str.count('\n')+1
		self.val_list = [float(x) for x in mat_str.split()]
		self.ncols = len(self.val_list) // self.nrows

		self.rows = [self.val_list[self.ncols*rownum:self.ncols*(rownum+1)] for rownum in range(self.nrows)]
		self.columns = [self.val_list[colnum::self.ncols] for colnum in range(self.ncols)]
