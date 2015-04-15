class Garden():
	
	def __init__(self,lines_of_plants, students = None):
		self.line1, self.line2 = lines_of_plants.split("\n")
		self.class_size = len(self.line1)/2
		self.plant_dict = {
		'V':'Violets',
		'R':'Radishes',
		'C':'Clover',
		'G':'Grass'
		}
		if students == None:
			self.students = ['A','B','C','D','E','F','G','H','I','J','K','L']
		else:
			self.students = sorted([entry[0] for entry in students])
	
	def plants(self,name):
		self.name = name[0]
		self.student_index = self.students.index(self.name) #could also use enumerate
		
		self.line1_answer = self.line1[self.student_index*2:self.student_index*2 +2]
		self.line2_answer = self.line2[self.student_index*2:self.student_index*2 +2]
		
		answer = []
		
		for letter in self.line1_answer:
			answer.append(self.plant_dict[letter])
		for letter in self.line2_answer:
			answer.append(self.plant_dict[letter])
		return answer
