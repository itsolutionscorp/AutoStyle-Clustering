#exercism -- kindergarten-garden
#author: fractalic

import itertools

def list_plant_groups(diagram_row):
	'''Group the plants in the given row'''
	return map(list,itertools.zip_longest(*[iter(diagram_row)]*2))

class Garden:
	'''Map potted plants to a bunch of kindergar(t|d)eners.'''

	plant_code_table = {'C':'Clover', 'G':'Grass', 'R':'Radishes', 'V':'Violets'}

	def __init__(self, diagram, students="Alice Bob Charlie David Eve\
	Fred Ginny Harriet Ileana Joseph Kincaid Larry".split()):
		'''Parse the plant layout and the student names.'''
		rows = diagram.split()
		students = sorted(students)
		top_row=list_plant_groups(rows[0])
		bot_row=list_plant_groups(rows[1])
		plant_map = list(map(lambda x:''.join(x[0]+x[1]),zip(top_row,bot_row)))
		self.plant_map = dict(zip(students,plant_map))

	def plants(self, student):
		'''Given a student, determine which plants they should care for.'''
		plant_list = [self.plant_code_table[plant] for plant in self.plant_map[student]]
		return plant_list
		
