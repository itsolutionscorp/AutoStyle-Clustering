class Allergies(object):
	
	def __init__(self, count):
		self.count = count

	def is_allergic_to(self, alle):
		allergis={
		"eggs" : 1, "peanuts" : 2, "shellfish": 4, "strawberries": 8, "tomatoes": 16, "chocolate": 32, "pollen": 64, "cats" :128}
		
