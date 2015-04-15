#!/usr/bin/python -tt

class Allergies(object):
	
	def __init__(self,score):
		self.score = score
		self.dict = {'eggs':1,'peanuts':2,'shellfish':4,'strawberries':8,'tomatoes':16,'chocolate':32,'pollen':64,'cats':128}
		self.list = sorted([item for item in self.dict if self.is_allergic_to(item)], key=self.retr_num)
	
	def retr_num(self,item):
	#Retrieves number associated with element to sort list of allergies
		return self.dict[item]
		
	def is_allergic_to(self,item):
	#Tests if allergie is present
		if int(bin(self.dict[item]),2) & int(bin(self.score),2) == 0b0:
			return False
		return True
