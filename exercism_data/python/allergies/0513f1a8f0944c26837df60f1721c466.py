class Allergies:
	list =[]
	def __init__(self,nummer):
		self.allnum = str(bin(256 + nummer)[2:])
		self.allnum = self.allnum[::-1]
		self.list =[]
		self.add_al()
		
	def add_al(self):
		if int(self.allnum[0]) != 0:
			self.list.append('eggs')	
		if int(self.allnum[1]) != 0:
			self.list.append('peanuts')
		if int(self.allnum[2]) != 0:
			self.list.append('shellfish')
		if int(self.allnum[3]) != 0:
			self.list.append('strawberries')
		if int(self.allnum[4]) != 0:
			self.list.append('tomatoes')
		if int(self.allnum[5]) != 0:
			self.list.append('chocolate')
		if int(self.allnum[6]) != 0:
			self.list.append('pollen')	
		if int(self.allnum[7]) != 0:
			self.list.append('cats')	

	def is_allergic_to(self,etwas):
		for i in self.list:
			if i == etwas:
				return True
		return False
		
				
				
