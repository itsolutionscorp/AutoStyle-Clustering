#!/usr/bin/python
# Copyright 2014 Jack Chi
# 
# Python Exercism Practice
# Using Generators to make Combinations of Integers to Sums
"""
	Each allergen has a score
"""
allergens = {
		1 : "eggs",
		2 : "peanuts",
		4 : "shellfish",
		8 : "strawberries",
		16: "tomatoes",
		32: "chocolate",
		64: "pollen",
		128: "cats"
	}

def allergyScores(allergens_list):
	"""
		Allergy score is a combination sum 
		of any set of the possible allergens
	"""
	yield (0,"") 
	c = []
 	for i, d in enumerate(allergens_list):			
 		for score in allergyScores(allergens_list[i+1:]):
 			yield (d + score[0], score[1] + allergens[d] + " ")

# Generate the list of all possible scores representing allergies
all_allergies = {}
for score, allergen in allergyScores(allergens.keys()):
	all_allergies[score] = allergen.split()

class Allergies:
	"""
		An Allergy Score can be mapped to a specific 
		combination of the known allergens. 

		For example: a score of 7 must mean the person is 
		allergic 1+2+4 [eggs, peanuts, shellfish]

		We begin by calculating all the known 
	"""
	def __init__(self, score=0):
		self.score= score
		try:
			self.list = all_allergies[score]
		except KeyError: 
			self.list = []

	def is_allergic_to(self, test):
		return test in self.list	
