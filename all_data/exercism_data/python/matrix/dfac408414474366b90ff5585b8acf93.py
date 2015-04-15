import numpy as np

class Fetcher:
	def __init__(self, matrix):
		self.matrix = matrix

class RowFetcher(Fetcher):
	def __getitem__(self, index):
		return self.matrix[index].tolist()[0]
		
class ColumnFetcher(Fetcher):
	def __getitem__(self, index):
		return self.matrix[:, index].reshape(-1).tolist()[0]
		
	
class Matrix():
	def __init__(self, text):
		self.matrix = np.matrix(';'.join(text.split('\n')))
		self.rows = RowFetcher(self.matrix)
		self.columns = ColumnFetcher(self.matrix)
