class Garden:

	DEFAULTSTUDENTS = [
		'Alice','Bob','Charlie','David',
		'Eve','Fred','Ginny','Harriet',
		'Ileana','Joseph','Kincaid','Larry'
	]

	PLANTNAMES = {
		'C': 'Clover', 'G': 'Grass',
		'R': 'Radishes', 'V': 'Violets'
	}	
	def __init__(self, bed, students = DEFAULTSTUDENTS):
		self.bed = bed.upper().split('\n')
		self.students= students
		print students 
		
	def plants(self, name):
		start= self.students.index(name)*2
		studentsPlants = self.bed[0][start:start+2] + self.bed[1][start:start+2] 
		return [Garden.PLANTNAMES[p] for p in studentsPlants]
