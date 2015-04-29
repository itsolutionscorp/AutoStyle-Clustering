from collections import OrderedDict

class Allergies:

	def __init__(self, test):
		self.foodcheck = ('eggs', 'peanuts', 'shellfish', 'strawberries', 'tomatoes', 'chocolate', 'pollen', 'cats')
		self.score = test

	def is_allergic_to(self, food):
		binaryconvert = list(str(bin(self.score)[2:])) #converts number to binary and makes it into a list
		# need it to be length 8
		binaryconvert = list(str(0)*(8-len(binaryconvert))) + binaryconvert #add sufficient number of zeroes to the beginning of list so there is 8 items
		binaryconvert.reverse()
		check = self.foodcheck.index(food) + 1
		return binaryconvert[check - 1] == "1"

	@property		
	def list(self):
		allergicfoods = []
		for i in self.foodcheck:
			if self.is_allergic_to(i) == True:
				allergicfoods.append(i)
		return allergicfoods
