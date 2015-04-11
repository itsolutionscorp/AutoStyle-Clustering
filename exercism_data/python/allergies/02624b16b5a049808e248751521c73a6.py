#!/usr/bin/env python

class Allergies(object):
	def __init__(self, number_allergies):
		self.number_allergies = number_allergies
		self.list =[]
		
		key_dict = {
		0:'eggs',
		1:'peanuts',
		2:'shellfish',
		3:'strawberries',
		4:'tomatoes',
		5:'chocolate',
		6:'pollen',
		7:'cats'
		}
		
		counter = 0
		while self.number_allergies >  2 ** counter:
			counter += 1
		
		while counter >= 0:
			if self.number_allergies/(2**counter):
				if counter in key_dict:
					self.list = [key_dict.get(counter)] + self.list
				self.number_allergies -= 2 ** counter
			counter -= 1
	def is_allergic_to(self, key):
		return key in self.list
