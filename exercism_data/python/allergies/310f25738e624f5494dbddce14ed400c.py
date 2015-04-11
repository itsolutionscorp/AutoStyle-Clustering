class Allergies:
	def __init__(self,value):
		self.value = value
		binarray = [int(b) for b in '{:08b}'.format(value)]
		binarray.reverse()
		items = ['eggs','peanuts','shellfish','strawberries','tomatoes','chocolate','pollen','cats']
		self.list = [i for i in items if binarray[items.index(i)] == 1]

	def is_allergic_to(self,item):
		return item in self.list
