class Allergies: 
	def __init__(self, score): 
		self.score = score
		self.allergies = [
			"eggs", 
			"peanuts", 
			"shellfish", 
			"strawberries", 
			"tomatoes", 
			"chocolate", 
			"pollen", 
			"cats"]
			
		self.list = []
		self.list_of_index = []
		
		import math
		
		if self.score > 255: 
			self.score = 255
		while self.score > 0: 
			index = (int(math.floor(math.log(self.score, 2))))
			self.list_of_index.append(index)
			self.score = self.score - 2**index
		
		#not sure if this is necessary, but couldn't figure out how to put the list in the
		#right order otherwise.
		self.list_of_index = sorted(self.list_of_index)
		for x in self.list_of_index: 
			self.list.append(self.allergies[x])	

# the value of each allergy is equal to 2**index of that item in the list	
# last item = self.allergies[-1]	
	def is_allergic_to(self, item): 
		if item in self.list: 
			return True
		return False
		
