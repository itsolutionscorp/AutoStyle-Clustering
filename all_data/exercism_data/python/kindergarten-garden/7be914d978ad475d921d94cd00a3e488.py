class Garden(object):

	def __init__(self,garden,students=[]):
		self.garden = garden
		self.students = sorted(students) or ['Alice','Bob','Charlie','David','Eve','Fred','Ginny','Harriet','Ileana','Joseph','Kincaid','Larry']
		self.plantsType = {'V':'Violets','C':'Clover','G':'Grass','R':'Radishes'}
		
	def plants(self,name):
		i = self.students.index(name)
		l = len(self.garden)+1
		sPlants = self.garden[i*2:i*2+2]+self.garden[(l/2)+i*2:(l/2)+i*2+2]
		out = []
		for c in sPlants:
			out.append(self.plantsType[c])
		return out
