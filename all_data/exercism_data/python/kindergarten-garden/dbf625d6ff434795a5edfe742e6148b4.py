class Garden:

	def __init__(self, garden, students=[]):
		self.garden = garden.replace("\n", "")
		if not students:
			self.students = ["Alice", "Bob", "Charlie", "David", "Eve", "Fred", "Ginny", "Harriet", "Ileana", "Joseph", "Kincaid", "Larry"]
		else:
			students.sort()
			self.students = students

	def plants(self, name):
		position = self.students.index(name)
		plantlist = []
		convertedlist = []
		plantlist.append(self.garden[position*2])
		plantlist.append(self.garden[position*2 + 1])
		plantlist.append(self.garden[(position*2) + (len(self.garden)/2)])
		plantlist.append(self.garden[(position*2 + 1 + len(self.garden)/2)])
		for plant in plantlist:
			if plant == "G": 
				convertedlist.append("Grass")
			elif plant == "C": 
				convertedlist.append("Clover")
			elif plant == "R": 
				convertedlist.append("Radishes")
			elif plant == "V": 
				convertedlist.append("Violets")
		return convertedlist
