class Garden:
	_students = ["Alice", 
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

	def __init__(self, plants, students=None):
		if not students: 
			students = self._students
		students = sorted(students)
		self.garden = {student: self._form_garden(index, plants) for index, student in enumerate(students)}

	def plants(self, student):
		if student in self.garden:
			return self.garden[student]
		return []

	def _form_garden(self, index, plants):
		(close, far) = plants.split('\n')
		index = index * 2
		close = close[index : index + 2]
		far = far[index : index + 2]
		student = [_to_plant(char) for char in close + far]
		return student

def _to_plant(char):
	if char == 'R':
		return 'Radishes'
	if char == 'V':
		return 'Violets'
	if char == 'G':
		return 'Grass'
	if char == 'C':
		return 'Clover'
	return None
