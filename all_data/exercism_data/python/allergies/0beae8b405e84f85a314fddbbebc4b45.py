# examine an allergy code to determine the associated allergies
class Allergies:
	allergy_code = 0
	list = []

	allergen_dict = {'eggs':1, 'peanuts':2, 'shellfish':4, 'strawberries':8, 'tomatoes':16, 'chocolate':32, 'pollen':64, 'cats':128}

	code_dict = {code:allergen for allergen,code in allergen_dict.items()}

	# create a allergy object with a given code
	def __init__(self, code=0):
		self.list = []
		self.allergy_code = code

		# look at each allergy, and create a list of all allergens in the code
		# the test suite requires the allergens ordered by code
		sorted_codes = sorted(self.code_dict.keys())
		for check_code in sorted_codes:
			if (code & check_code):
				self.list.append(self.code_dict[check_code])


	# check if the code matches a given allergen
	def is_allergic_to(self, allergen):
		if (self.allergy_code & self.allergen_dict[allergen]):
			return True

		return False
