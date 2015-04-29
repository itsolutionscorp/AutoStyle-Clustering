class Allergies(object):
	def __init__(self, score):
	    self.score = score
	    while self.score >= 256:
	        self.score -= 256
	    self.list = self.lst_allergies()
	    
	allergies = {1: 'eggs',
	              2: 'peanuts',
	              4: 'shellfish',
	              8: 'strawberries',
	              16: 'tomatoes',
	              32: 'chocolate',
	              64: 'pollen',
	              128: 'cats'}
	    
	def lst_allergies(self):
	    lst = []
	    num = 128
	    score = self.score
	    while num > 0:
	        if score - num >= 0:
	            lst.insert(0, self.allergies[num])
	            score -= num
	        num /= 2
	    return lst
		
	def is_allergic_to(self, allergy):
	    if allergy in self.lst_allergies():
	        return True
	    return False
