class SumOfMultiples: 

	def __init__(self, multiple_a = None, multiple_b = None, multiple_c = None): 
		"""Takes up to 3 multiples. If first two are not given, 
			they are set as 3 and 5 respectively."""
		self.multiple_a = multiple_a or 3
		self.multiple_b = multiple_b or 5
		self.multiple_c = multiple_c or None
		self.sum = 0
		
	def to(self, n): 
		"""Takes integer to find sum of multiples up to. Returns sum."""
		for number in range(1, n): 
			if not self.multiple_c:
				if number % self.multiple_a == 0 or number % self.multiple_b == 0: 
					self.sum += number
			else: 
				if (number % self.multiple_a == 0 or 
					number % self.multiple_b == 0 or 
					number % self.multiple_c == 0): 
					self.sum += number
		return self.sum
