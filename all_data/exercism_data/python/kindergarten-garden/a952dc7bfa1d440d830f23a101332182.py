# represents a (possibly) multi-row kindergarten garden
class Garden(object):
	# plants must be of type str consisting of lines of equal length and consist of keys from plant_initials
	def __init__(self,plants,students='Alice Bob Charlie David Eve Fred Ginny Harriet Ileana Joseph Kincaid Larry'.split(),plants_per_student=2,plant_initials={'C':'Clover', 'G':'Grass', 'R':'Radishes', 'V':'Violets'}):
		self.plant_lists=plants.split('\n')
		self.students=sorted(students)
		self.plants_per_student=plants_per_student
		self.plant_initials=plant_initials

	# translates a string of characters into the corresponding list of plant names
	def initials_to_strings(self,initials):
		answer=[]
		for c in initials:
			answer.append(self.plant_initials[c])
		return answer

	# figures out which plants belong to student
	def plants(self,student):
		answer_initials=''
		student_index=self.students.index(student)
		for line in self.plant_lists:
			answer_initials+=line[student_index*self.plants_per_student:(student_index+1)*self.plants_per_student]
		return self.initials_to_strings(answer_initials)
