#/usr/bin/env python
class Garden:
	plantdict = {'V':'Violets', 'R':'Radishes', 'C':'Clover', 'G':'Grass'}
	def __init__(self,gard,students = ['Alice','Bob','Charlie','David','Eve','Fred','Ginny','Harriet','Ileana','Joseph','Kincaid','Larry']):
		self.gard = gard.splitlines()
		self.students = sorted(students)
	def plants(self,stud):
		if stud in self.students:
			numstud = self.students.index(stud)
			result = [self.plantdict[self.gard[0][numstud*2]], self.plantdict[self.gard[0][numstud*2+1]], self.plantdict[self.gard[1][numstud*2]], self.plantdict[self.gard[1][numstud*2+1]]]
			return result
		else: raise ValueError('Such student does not exist.')
