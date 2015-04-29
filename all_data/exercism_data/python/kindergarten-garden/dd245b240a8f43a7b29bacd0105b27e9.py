DEFAULT_KIDS = ["Alice", 
	    		"Bob", 
			    "Charlie", 
			    "David", 
			    "Eve", 
			    "Fred", 
			    "Ginny", 
			    "Harriet", 
			    "Ileana", 
			    "Joseph", 
			    "Kincaid", 
			    "Larry"]

PLANT_NAMES =  ["Grass", 
				"Violets", 
				"Clover", 
				"Radishes"]

PLANT_CODES =  ["G",
				"V",
				"C",
				"R"]

class Garden(object):

	def __init__(self, garden, students=DEFAULT_KIDS):
		self.garden = garden.split('\n')
		self.students = sorted(students)

	def plants(self, kid):

		#get index for kid to use as cross-reference against garden list
		kid_index = 0
		for i in range(len(self.students)):
			if kid == self.students[i]:
				kid_index = i*2

		#get plant codes for kid_index from self.garden
		plant_codes = self.garden[0][kid_index:kid_index+2] + self.garden[1][kid_index:kid_index+2]

		#translate plant codes to names and build list to return
		return_list = []
		for plant_code in plant_codes:
			for i in range(len(PLANT_CODES)):
				if plant_code == PLANT_CODES[i]:
					return_list.append(PLANT_NAMES[i])

		return return_list
