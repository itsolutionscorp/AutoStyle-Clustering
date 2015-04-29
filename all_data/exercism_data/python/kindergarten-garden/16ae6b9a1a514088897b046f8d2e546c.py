ordered_children = [
	'Alice', 'Bob', 'Charlie', 'David',
	'Eve', 'Fred', 'Ginny', 'Harriet',
	'Ileana', 'Joseph', 'Kincaid', 'Larry'
]

interval = 2

flowerKey = {
	'R': 'Radishes',
	'C': 'Clover',
	'G': 'Grass',
	'V': 'Violets'
}

class Garden(object):
	def __init__(self, garden, students = ordered_children):
		self.parse_gardenMap(garden, students)

	def parse_gardenMap(self, garden, students):
		students, self.gardenMap = sorted(students), {}
		for i, row in enumerate(garden.split()):
			child = students[0]
			for j, plant in enumerate(row):
				j+=1
				try:
					self.gardenMap[child].append(flowerKey[plant])
				except KeyError:
					self.gardenMap[child] = [flowerKey[plant]]
				if j % 2 == 0 :
					try:
						childIndex = j / 2
						child = students[childIndex]
					except:
						pass

	def plants(self, child):
		return self.gardenMap[child]
