class Binary:
	def __init__(self, b):
		bl = [1 if dig == '1' else 0 for dig in b]
		self.n = sum([1<<(len(bl)-1-i) for i in range(len(bl)) if bl[i] == 1])
		
	def __int__(self):
		return self.n
