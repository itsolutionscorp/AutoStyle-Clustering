class Garden(object):

	defaultNames = "Alice, Bob, Charlie, David, Eve, Fred, Ginny, Harriet, Ileana, Joseph, Kincaid, Larry".split(', ')

	def __init__(self, diagram, students=defaultNames):
		self.names = sorted(students)
		self.items = {}
		length = len(diagram)
		for i in range(0,(length-2)/4+1):
			self.items[self.names[i]] = [diagram[i*2], diagram[i*2+1], diagram[i*2+length/2+1], diagram[i*2+length/2+2]]

	def plants(self,name):
		plants = self.items[name]
		fullNames = dict(V='Violets', C='Clover', G='Grass', R='Radishes')
		for n,item in enumerate(plants):
			plants[n] = fullNames[item]
		return plants
