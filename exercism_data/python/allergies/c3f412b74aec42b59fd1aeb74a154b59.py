class Allergies:
	
	allergy_table = {
		7: 'eggs',
		6: 'peanuts',
		5: 'shellfish',
		4: 'strawberries',
		3: 'tomatoes',
		2: 'chocolate',
		1: 'pollen',
		0: 'cats'
	}
	
	def __init__(self, number):
		self.number = '{:08b}'.format(number % 256)
		self.list = []
		for i in range(8):
			if self.number[i] == '1':
				self.list.insert(0, self.allergy_table[i])
		
	
	def is_allergic_to(self, allergen):
		return allergen in self.list
