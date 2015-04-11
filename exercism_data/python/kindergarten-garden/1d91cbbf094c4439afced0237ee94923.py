
class Garden():
	def __init__(self, cups, students="Alice Bob Charlie David Eve Fred Ginny Harriet Ileana Joseph Kincaid Larry"):
		self.windowsill = cups.split()
		try:
			self.students = students.split()
		except AttributeError:
			self.students = students
			self.students.sort()
		self.plantDict = {'R': "Radishes", 'C': "Clover",
							'G': "Grass", 'V': "Violets"}
	
	def plants(self, student):
		stunum = self.students.index(student)
		lettercodes = [self.windowsill[i][2*stunum:2*stunum+2] for i in range(len(self.windowsill))]
		plants_array = []
		for row in lettercodes:
			plants_array += row
		plants = []
		for letter in plants_array:
			plants += [self.plantDict[letter]]
		return plants
