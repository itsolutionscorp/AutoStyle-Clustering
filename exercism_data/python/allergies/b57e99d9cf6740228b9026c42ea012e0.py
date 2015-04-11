allergies_list = 'eggs peanuts shellfish strawberries tomatoes chocolate pollen cats'.split()

class Allergies:
	allergies = ''
	list = []
	
	def is_allergic_to(self,id):
		if allergies_list.index(id) > len(self.allergies)-1:
			return False
		else:
			return self.allergies[allergies_list.index(id)]
		
	def __init__(self,amount):
		amount = amount & 0b11111111
		# I store the allergies in reverse order as it is the "natural" order for the list
		self.allergies = str(bin(amount)[2:])[::-1]
		listado = []
		if amount == 0:
			return
		for a in range(len(self.allergies)): #range(len(allergies)-1,-1,-1):
			if self.allergies[a] == '1':
				listado.append(allergies_list[a])
			self.list = listado
		return
