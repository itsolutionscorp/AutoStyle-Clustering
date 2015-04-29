class Allergies(object):
	
	allers_numbers = [128, 64, 32, 16, 8, 4, 2, 1]
	allers = ['cats', 'pollen', 'chocolate', 'tomatoes', 
		'strawberries', 'shellfish', 'peanuts', 'eggs']
	
	
	def __init__(self, number):
		self.number = number % 256	
		self.list = self.list()
		self.allers_numbers = [128, 64, 32, 16, 8, 4, 2, 1]
		self.allers = ['cats', 'pollen', 'chocolate', 'tomatoes', 
			'strawberries', 'shellfish', 'peanuts', 'eggs']
		
		
	def allergy_list_func(self):
		
		aller_true_false = []
		
		for n in range(0, len(self.allers_numbers)):
			
			if self.allers_numbers[n] <= self.number:
				self.number -= self.allers_numbers[n]
				aller_true_false.append(True)
			else:
				aller_true_false.append(False)
				
		return aller_true_false
		
	def is_allergic_to(self, aller):
		
		if aller in self.list:
			return True

	def list(self):
		
		this_list = []
		allergy_list = self.allergy_list_func()

		for n in range(len(allergy_list)-1, -1, -1):
			if allergy_list[n] == True:
				this_list.append(self.allers[n])
				
		return this_list
		
print Allergies(1).is_allergic_to('eggs')
