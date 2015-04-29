ALLERGIES_MAP = ['eggs',
				 'peanuts',
				 'shellfish',
			     'strawberries',
			     'tomatoes',
			     'chocolate',
			     'pollen',
			     'cats']

class Allergies(object):
	def __init__(self, num):
		bin_str = "{0:b}".format(num)
		num_to_check = min(len(bin_str), len(ALLERGIES_MAP))
		self.list = [ALLERGIES_MAP[i] for i in xrange(0, num_to_check)
									if bin_str[-i-1] == '1']

	def is_allergic_to(self, allergen):
		return allergen in self.list
		
