#exercism -- kindergarten-garden
#author: fractalic

import itertools
from collections import defaultdict

class Garden:
	'''Map potted plants to a bunch of kindergar(t|d)eners.'''

	plant_code_table = {'C':'Clover', 'G':'Grass', 'R':'Radishes', 'V':'Violets'}
	student_plants = {}
	students = []

	def __init__(self, diagram, students="Alice Bob Charlie David Eve\
	Fred Ginny Harriet Ileana Joseph Kincaid Larry".split()):
		'''Parse the plant layout and the student names.'''
		self.student_plants = defaultdict(list)

		# Intended to split at a platform-independent newline.
		rows = diagram.split()

		# Collect the groups of plants, on each row, belonging to each student.
		self.students = sorted(students)
		for row in rows:
			self.append_plants(row)


	def plants(self, student):
		'''Given a student, determine which plants they should care for.'''
		plant_list = [self.plant_code_table[plant]\
		 for plant in self.student_plants[student]]
		return plant_list

	def append_plants(self, plants):
		'''Take multiple plants at a time and bind to a student'''
		group = 2

		for i in range(int(len(plants)/group)):
			for plant in plants[group*i : group*i+group]:
				# The students list is sorted in dictionary order,
				# so append ith group to ith student in dictionary.
				self.student_plants[self.students[i]].append(plant)

		
