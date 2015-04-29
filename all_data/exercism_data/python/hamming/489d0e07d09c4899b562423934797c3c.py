class HammingTest():
	
	def __init__(self, name):
		self.name = name
		
	def distance(self, phrase1, phrase2):
		self.first = phrase1
		self.second = phrase2
		count = 0
		for x,y in zip(self.first,self.second):
			if x == y:
				count += 0
			else:
				count += 1
		return count

hamming = HammingTest('first')
	































'''def distance(self, phrase1, phrase2):
	self.first = phrase1
		self.second = phrase2
		count = 0
		for x,y in zip(self.first,self.second):
			if x == y:
				count += 0
			else:
				count += 1
	return count'''
