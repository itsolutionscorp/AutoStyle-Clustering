class Allergies:

	allergens = [
	
	'eggs',
	'peanuts',
	'shellfish',
	'strawberries',
	'tomatoes',
	'chocolate',
	'pollen',
	'cats'
	]

	def __init__(self, score):
		self.score = list(bin(score)[2:])[::-1] #reversing binary of score since allergens list is in increasing order
		allergic_to = zip(self.allergens, self.score) #creates tuple of (allergen, (1 or 0))...
		self.list = [ i[0] for i in allergic_to if i[1] == '1' ] #...and returns list of allergens if score is 1 (true)
	
	def is_allergic_to(self, allergen):
		return allergen in self.list
