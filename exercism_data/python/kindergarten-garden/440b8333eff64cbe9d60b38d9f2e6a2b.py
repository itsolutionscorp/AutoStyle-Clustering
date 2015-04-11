class Garden(object):
	def __init__(self, rows, students = ['Alice','Bob','Charlie','David','Eve','Fred','Ginny','Harriet',
										'Ileana','Joseph','Kincaid','Larry']):
		self.rows = rows.split()
		students.sort()
		self.students = students
		self.guide = {'G':'Grass', 'C':'Clover', 'R':'Radishes','V':'Violets'}
		self.make_map()
		
	def make_map(self):
		rows = self.rows
		max = [rows[0][i:i+2]+rows[1][i:i+2] for i in range(0,len(rows[0]),2)]
		self.map = dict(zip(self.students, max))
		
	def plants(self, name):
		return [self.guide[plant] for plant in self.map[name]]
		
		
