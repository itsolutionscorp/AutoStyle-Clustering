import operator

allergies = { 'eggs': 1, 'peanuts': 2, 'shellfish': 4, 'strawberries': 8, 'tomatoes': 16, 'chocolate': 32, 'pollen': 64, 'cats': 128 } 
	
class Allergies(object):

	def __init__(self, n):
		self.numberofallergies = n
		self.list = self.is_allergic_to(n)

	def is_allergic_to(self, s):

		collection = []	

		if self.numberofallergies == 255:
			sorted_x = sorted(allergies.items(), key=operator.itemgetter(1))
			for x in range(len(sorted_x)):
				collection.append(sorted_x[x][0])
			return collection

		if isinstance(s, int):
			for allergie in allergies:
				if s & allergies[allergie] == allergies[allergie]:
					collection.append(allergie)
			return collection
		elif isinstance(s, str):
			for allergie in allergies:
				if self.numberofallergies & allergies[s] == allergies[s]:
					return True
