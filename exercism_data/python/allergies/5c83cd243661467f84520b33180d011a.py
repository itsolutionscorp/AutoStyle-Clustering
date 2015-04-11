#!/usr/bin/python
# Copyright 2014 Jack Chi
# 
# Python Exercism Practice:
# Using BitString Representation to match
# Integers to Powers of 2 
"""
	Each allergen has a score
"""
ALLERGENS = [
		"eggs",
		"peanuts",
		"shellfish",
		"strawberries",
		"tomatoes",
		"chocolate",
		"pollen",
		"cats"
	]

class Allergies:
	"""
		An Allergy Score can be mapped to a specific 
		combination of the known allergens. 

		For example: a score of 7 must mean the person is 
		allergic 1+2+4 [eggs, peanuts, shellfish]

	"""
	def __init__(self, score=0):
		self.score= score
		binScore = "{0:b}".format(score)
		minCheck = min(len(binScore), len(ALLERGENS))
		results =[] # cannot use list comprehension here because it needs to be exact sum
		for i in xrange(minCheck):
			if binScore[-i-1] == '1': 
				results.append(ALLERGENS[i])
				score-= 2**i
		self.list = results if score ==0 else []		
	def is_allergic_to(self, test):
		return test in self.list	
