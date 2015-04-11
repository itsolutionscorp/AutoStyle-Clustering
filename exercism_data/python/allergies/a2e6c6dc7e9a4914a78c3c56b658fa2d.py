#!/usr/bin/env python
 # -*- coding: utf-8 -*-
 
class Allergies:
	def __init__(self, score):
		if not isinstance(score, int):
			raise ValueError("score must be an integer")
		
		self.allergens = ["eggs", "peanuts", "shellfish", "strawberries", "tomatoes", "chocolate", "pollen", "cats"]
		self.score = score
		self.list = [x for x in self.allergens if 2**self.allergens.index(x) & score != 0]
		
	def is_allergic_to(self, thing):
		if not isinstance(thing, "".__class__):
			raise ValueError("thing must be a string")
			
		return self.score & 2**self.allergens.index(thing) != 0
