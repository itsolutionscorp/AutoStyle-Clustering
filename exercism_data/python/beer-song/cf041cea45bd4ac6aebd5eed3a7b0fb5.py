'''
	This program simple sings out the Beer Song
'''
class Beer(object):
	'''
		Define instance attributes
	'''
	def __init__(self):
		self.max_bottles = 99
		self.placeholder = "<num>"
		self.verses = ["Take one down and pass it around, " + self.placeholder + " of beer on the wall.\n", "Go to the store and buy some more, " + str(self.max_bottles) + " bottles of beer on the wall.\n"]
		self.first_verse =  self.placeholder + " of beer on the wall, " +  self.placeholder + " of beer.\n"
		self.quantity = ["bottle", "bottles"]

	'''
		Given a number, sing the verse with that many number of bottles
	'''
	def verse(self, num_bottles):
		verse = self.first_verse
		bottles = self.getNumBottles(num_bottles)
		if num_bottles == 0:
			verse = verse.replace(self.placeholder, bottles, 2).capitalize()
			verse += self.verses[1]
		else:
			verse = verse.replace(self.placeholder, bottles, 2)
			if num_bottles == 1:
				verse += self.verses[0].replace("one", "it").replace(self.placeholder, self.getNumBottles(num_bottles-1))
			else:
				verse += self.verses[0].replace(self.placeholder, self.getNumBottles(num_bottles-1))

		return verse

	'''
		If a given a range of numbers, sing the song for that range.
		Otherwise sing till no bottles remain
	'''
	def sing(self, upper_limit, lower_limit=0):
		result = ""
		while upper_limit >= lower_limit:
			result += self.verse(upper_limit) + "\n"
			upper_limit -= 1

		return result

	'''
		Helper method to generate the string with the number of bottles
	'''
	def getNumBottles(self, num_bottles):
		bottles = self.quantity[0] if num_bottles == 1 else self.quantity[1]
		if num_bottles == 0:
			return "no more " + bottles
		return str(num_bottles) + " " + bottles
