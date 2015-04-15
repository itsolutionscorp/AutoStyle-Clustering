'''garden.py
	created 8 Oct 2014
	by @jestuber '''
import string

PLANTNAMES = {'R':'Radishes',
				'V':'Violets',
				'G':'Grass',
				'C':'Clover'}

class Garden(object):
	"""docstring for Garden"""
	def __init__(self, plantLayout, students=('Alice', 'Bob', 'Charlie', 'David', 'Eve', 'Fred', 'Ginny', 'Harriet', 'Ileana', 'Joseph', 'Kincaid', 'Larry')):
		super(Garden, self).__init__()
		self.plantLayout = plantLayout
		self.students = tuple(sorted(students))

	def plants(self, name):
		''' returns plants owned by name'''
		k = self.students.index(name)*2
		kidsPlants = self.plantLayout[k:k+2] + self.plantLayout.split('\n',1)[1][k:k+2]
		return self.plantLetterToName(kidsPlants)

	def plantLetterToName(self,letters):
		global PLANTNAMES
		return [PLANTNAMES[c] for c in letters]
