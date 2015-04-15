
class Garden(object):
	def __init__(self, string, students=['Alice', 'Bob', 'Charlie', 'David', 
										'Eve', 'Fred', 'Ginny', 'Harriet', 
										'Ileana', 'Joseph', 'Kincaid', 'Larry']):
		self.string = string
		self.string = string.split('\n')
		self.students = sorted(students)
		
	def plants(self, student):
		stuseed = {}
		label = {'G':'Grass', 'C':'Clover', 'R':'Radishes', 'V':'Violets'}

## define and convert row 1 to labels
## any simpler mapping method I tried
## was giving me errors
		row1 = list(self.string[0])
		count = 0
		for l in row1: 
			row1[count] = label[l]
			count += 1
		suffix1 = 48 - len(row1)
		
## append . for unknown seeds
		for suff in range(suffix1): row1.append('.')
		
##row2 mapping
		row2 = list(self.string[1])
		count = 0
		for l in row2:
			row2[count] = label[l]
			count += 1
		suffix2 = 48 - len(row2)
		
## row 2 unknown seeds
		for suff in range(suffix2): row2.append('.')
		count = 0
		for person in self.students:
			stuseed[person] = [row1[count], row1[count+1],
								row2[count], row2[count+1]]
			count += 2
		return stuseed[student]

		
