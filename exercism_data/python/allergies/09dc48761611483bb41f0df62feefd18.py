class Allergies:
	"""Given personal allergy score prepare list of allergies"""

	allergy_scores = [
		{'cats': 128},
		{'pollen': 64},
		{'chocolate': 32},
		{'tomatoes': 16},
		{'strawberries': 8},
		{'shellfish': 4},
		{'peanuts': 2},
		{'eggs': 1},
	]		# List instead of dictionary, because order is important for bit comparison

	def __init__(self, score):
		if score < 0:
			raise ValueError('Score has to be positive')
		self.score = score % 256		# Ignore non allergen score parts
		self.__bitscore = list(format(self.score,'08b'))		# Represent score in binary and make a list of bits
		self.list = [j.keys()[0] for i,j in zip(self.__bitscore,self.allergy_scores) if i=='1']		# Based and bits select appropriate allergies
		self.list.reverse()			# Reverse the list so that it's ascending according to allergy score

	def is_allergic_to(self, allergy):
		"""Tests wheter a given allery is on one's list of allergies"""
		return allergy in self.list

# Standalone debugging
if __name__ == '__main__':
	a = Allergies(5)
	print a.is_allergic_to('eggs')
	print a.list
