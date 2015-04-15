# exercism sum_of_multiples

def sum_int_multiples(factor, limit):
		'''Sum the integer multiples of the given factor,
		up the specified limit exclusive.'''

		sum = 0

		# Include all cases where i*factor < limit.
		for i in range(1, int(limit/factor) + 1):
			multiple = i*factor
			if multiple < limit:
				sum += multiple
		
		return sum

class SumOfMultiples:
	'''Sum the multiples of a given numbers, up to a limit exclusive.
	By default, to will produce the sum of the multiples of 3 and 5.'''

	def __init__(self, *multiples):
		self.multiples = []
		self.multiples_prime = self.multiples

		# Select 3 and 5 as multiples by default.
		if len(multiples):
			self.multiples = multiples

			# Generate a list of multiples, such that each element
			# is prime relative to the list.
			for multiple in multiples:
				relatively_prime = True
				for factor in multiples:
					# Numbers cannot be relatively prime to themselves.
					if ((multiple != factor) and multiple%factor == 0):
							relatively_prime = False
				if relatively_prime:		
					self.multiples_prime.append(multiple)
		else:
			self.multiples = [3,5]
			self.multiples_prime = self.multiples

	def to(self, limit):
		'''Sum the integer multiples of the given list of numbers,
		up to some limit exclusive.'''
		sum = 0
		
		for factor in self.multiples_prime:
			sum += sum_int_multiples(factor, limit)

		# Subtract multiples of every pairwise product of multiples.
		# (For example: 15, 30, ... were summed, twice, as multiples of both 3 and 5).
		for factor in self.multiples_prime:
			for x in self.multiples_prime:
				if x > factor:
					sum -= sum_int_multiples(x*factor, limit)
		return sum
