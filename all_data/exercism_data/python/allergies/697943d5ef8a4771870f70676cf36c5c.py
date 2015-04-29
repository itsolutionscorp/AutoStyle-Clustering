class Allergies(object):
	items = ['cats', 'pollen', 'chocolate', 'tomatoes', 'strawberries', 'shellfish', 'peanuts', 'eggs']

	def __init__(self, _allergyscore):
		self._allergyscore = _allergyscore
		self._binaryscore = bin(self._allergyscore)[2:][-8:].rjust(8, '0')
		self.list = []
		self._calculate_allergylist()

	def _calculate_allergylist(self):
		if self._allergyscore <= 0:
			return
		for binary_digit, allergy in zip(self._binaryscore, self.items):
			if binary_digit == '1':
				self.list.append(allergy)
		self.list.reverse()

	def is_allergic_to(self, item):
		'''
		TODO: Convert to simple lookup instead of looping through self.items
		'''
		if self._allergyscore <= 0:
			return False
		for binary_digit, allergy in zip(self._binaryscore, self.items):
			if allergy == item and binary_digit == '1':
				return True
		return False
