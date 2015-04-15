class Allergies(object):
	
	"""Allergies is a set of allergies for an individual. Given an allergy score, an Allergies will compute the list of allergens."""
	
	allergens = ['eggs','peanuts','shellfish','strawberries','tomatoes','chocolate','pollen','cats']

	def __init__(self, score):
		"""Initialize an Allergies class with an allergy score"""
		self.setScore(score)

	def is_allergic_to(self,item):
		"""Test whether the individual is allergic to a particular item"""
		if item.lower() in self.list: return True
		else: return False

	def setScore(self,score):
		"""Set the allergen score and associated allergens in the list"""
		self.score = score
		self.list = []

		self.binarySet = str(bin(score))[2:]						# Encode the score in binary; each digit is a possible allergen
																	# First digit = eggs, second digit = peanuts, etc...
		try:
			for x in range(len(self.binarySet)):
				if self.binarySet[-(x + 1)] == '1':					# Loop through backwards to check if an allergen is present
					self.list.append(Allergies.allergens[x])		# Add those that are from the ordered allergen master list

		except: return True 										# Only encountered when the number passed was too high



# Author's Notes on Coding Conventions:

	# I've coded to pep8 conventions with a few exceptions that
	# work nicely with my setup (I'm only coding for myself):
		# ignore = W191,E261,W293,E701
		# max-line-length = 120


# # Allergies

# Write a program that, given a person's allergy score, can tell them whether or not they're allergic to a given item, and their full list of allergies.

# An allergy test produces a single numeric score which contains the
# information about all the allergies the person has (that they were
# tested for).

# The list of items (and their value) that were tested are:

# * eggs (1)
# * peanuts (2)
# * shellfish (4)
# * strawberries (8)
# * tomatoes (16)
# * chocolate (32)
# * pollen (64)
# * cats (128)

# So if Tom is allergic to peanuts and chocolate, he gets a score of 34.

# Now, given just that score of 34, your program should be able to say:

# - Whether Tom is allergic to any one of those allergens listed above.
# - All the allergens Tom is allergic to.
