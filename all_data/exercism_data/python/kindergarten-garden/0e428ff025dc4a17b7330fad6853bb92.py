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
	def __init__(self, plantLayout, **students):
		super(Garden, self).__init__()
		self.plantLayout = plantLayout
		if students.values():
			self.students = sorted(students.values()[0])
		else:
			self.students = []
	def plants(self, name):
		''' returns plants owned by name'''
		if not self.students:
			k = string.uppercase.index(name[0])*2
		else: #student list was supplied
			# print type(self.students)
			k = self.students.index(name)*2
		kidsPlants = self.plantLayout[k:k+2] + self.plantLayout.split('\n',1)[1][k:k+2]
		return self.plantLetterToName(kidsPlants)

	def plantLetterToName(self,letters):
		global PLANTNAMES
		names = []
		for c in letters:
			names.append(PLANTNAMES[c])
		return names




# garden = Garden("VCRRGVRG\nRVGCCGCV",
#                         students="Samantha Patricia Xander Roger".split())
# print garden.plants("Patricia")


#string.lowercase.index('b')
#string.lowercase[2]
