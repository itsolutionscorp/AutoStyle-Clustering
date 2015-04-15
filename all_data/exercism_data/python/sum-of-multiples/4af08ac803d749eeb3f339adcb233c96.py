class SumOfMultiples:
	def __init__(self, *args):
		# Better way of setting the mutiplesof list
		self.multiplesof = args or [3, 5]

	def to(self,topLimit):
		multiples = []
		# Create a list of all multiples of all given bases
		for multiple in self.multiplesof:
			multiples += range(multiple,topLimit,multiple)
		# Use a set to make sure everything in the list is unique
		return sum(set(multiples))
