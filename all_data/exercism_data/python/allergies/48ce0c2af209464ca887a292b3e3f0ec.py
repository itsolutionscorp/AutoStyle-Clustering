class Allergies:
	map = [ 'eggs', 'peanuts', 'shellfish', 'strawberries', 'tomatoes', 'chocolate', 'pollen', 'cats' ]
	allergies = 0
	def __init__(self, allergy):
		self.allergies = allergy
	def is_allergic_to(self, x):
		if self.map.count(x) == 0: 
			return False
		return True if (self.allergies & ( 1 << self.map.index(x))  != 0) else False

	def list():
		temp = allergies
		i = 0
		output = []
		while temp:
			i += 1
			if (temp & 1):
				output.append(map[i])
			temp = temp >> 1
		return output
