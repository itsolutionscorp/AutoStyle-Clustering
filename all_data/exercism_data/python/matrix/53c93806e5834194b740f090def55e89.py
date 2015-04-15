
class Matrix():

	def __init__(self, values):
		nums = values.split('\n')
		rows = []
		for row in nums:
			rows.append(map(int, row.strip().split(' ')))

		self.rows = rows
		self.columns = map(list, zip(*self.rows))
