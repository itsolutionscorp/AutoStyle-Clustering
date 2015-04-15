class Matrix:
	def __init__(self, str):
		lines = str.splitlines()
		rows = []
		columns = []
		for line in lines:
			items = [int(x) for x in line.split()]
			rows.append(items)
			for ix in range(len(items)):
				if len(columns) <= ix:
					columns.append([])
				columns[ix].append( items[ix] )
		self.rows = rows
		self.columns = columns
