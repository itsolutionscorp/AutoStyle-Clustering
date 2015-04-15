"""
Difference between the sum of squares and the square of the sums.
Written by Bede Kelly for Exercism.
"""

__author__ = "Bede Kelly"

all_allergies = ["eggs",
				 "peanuts",
				 "shellfish",
				 "strawberries",
				 "tomatoes",
				 "chocolate",
				 "pollen",
				 "cats"]

class Allergies:
	def __init__(self, score):
		self.score = bin(score % 256)
		bin_string = reversed(str(self.score)[2:].zfill(8))
		self.list = []
		for has_allergy, allergy in zip(bin_string, all_allergies):
			if bool(int(has_allergy)):
				self.list.append(allergy)

	def is_allergic_to(self, allergy):
		return allergy in self.list
