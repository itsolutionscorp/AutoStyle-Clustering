class Allergies:
	list =[]
	def __init__(self,nummer):
		allergens = [
            'cats',
            'pollen',
            'chocolate',
            'tomatoes',
            'strawberries',
            'shellfish',
            'peanuts',
            'eggs'
        ]
		#convert to binary
		self.allnum = str(bin(256 + nummer)[2:])
		#alternative way of converting to binary
		#self.allnum = format (256+nummer,'08b')
		
		self.allnum = self.allnum[::-1]
		allergens = allergens[::-1]
		self.allnum_bina = [int(a) for a in self.allnum]
		self.list =[allergy for allergy,auswertung in zip(allergens, self.allnum_bina) if auswertung]
		

	def is_allergic_to(self,etwas):
		for i in self.list:
			if i == etwas:
				return True
		return False
		
				
				
