class SumOfMultiples:
	def __init__(self, *args):
		if len(args) == 0:
			self.multiplesof = [3, 5]
		else:
			self.multiplesof = args

	def to(self,topLimit):
		multiples = []
		# Create a list of all multiples of all given bases
		for multiple in self.multiplesof:
			multiples = multiples + range(multiple,topLimit,multiple)
		# Use a set to make sure everything in the list is unique
		return sum(set(multiples))
