class Garden():
	def plants(self,string):
		plantsDict={
			'V':'Violets',
			'R':'Radishes',
			'C':'Clover',
			'G':'Grass'
		}

		gardenLocation = self.students[string[0]]
		garden = self.garden.split('\n')
		plantsList = garden[0][gardenLocation[0]] + garden[0][gardenLocation[1]] + garden[1][gardenLocation[0]] + garden[1][gardenLocation[1]]
		return [plantsDict[i] for i in plantsList]

	def __init__(self,jardin,students=['Alice','Bob','Charlie','David','Eve','Fred','Ginny','Harriet','Ileana','Joseph','Kincaid','Larry']):
		students = sorted(students)
		self.students= {students[i][0]:[2*i,2*i+1] for i in range(len(students))}
		self.garden = jardin
