class SumOfMultiples(object):
	def __init__(self, multiple_a = None, multiple_b = None, multiple_c = None):
		self.base_multiples = [3,5]
		self.multiples = []
		self.multiple_c = None

		if multiple_a!=None:
			self.multiples.append(multiple_a)
		else:
			self.multiples = self.base_multiples	
		if multiple_b!=None:
			self.multiples.append(multiple_b)
		if multiple_c!=None:
			self.multiple_c = multiple_c
			self.multiples.append(multiple_c)
				
	def to(self,top_of_range):
		if self.multiple_c != None:
			sum_of_multiples = sum([i for i in range(top_of_range) 
				if i % self.multiples[0] == 0 or i % self.multiples[1] == 0 
				or i % self.multiples[2] == 0])
		else:	
			sum_of_multiples = sum([i for i in range(top_of_range) 
				if i % self.multiples[0] == 0 or i % self.multiples[1] == 0])
		return sum_of_multiples

		
