class Garden(object):
	DEFAULTSTUDENTS = """Alice Bob Charlie David
						Eve Fred Ginny Harriet
						Ileana Joseph Kincaid Larry""".split()

	def __init__(self, cups = "VVVCCCRVCG\nCCVVRRRVGC", students = DEFAULTSTUDENTS):
		self.cups = cups.split()
		self.students = sorted(students)

	def plants(self, student):
		plantdict = {"V": "Violets", "R":"Radishes", "C":"Clover", "G":"Grass"}
		results = []
		results.append(plantdict[self.cups[0][self.students.index(student)*2]])
		results.append(plantdict[self.cups[0][self.students.index(student)*2+1]])
		results.append(plantdict[self.cups[1][self.students.index(student)*2]])
		results.append(plantdict[self.cups[1][self.students.index(student)*2+1]])

		return results
