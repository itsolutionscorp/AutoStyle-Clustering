plants = { 'G': 'Grass', 'V': 'Violets', 'R': 'Radishes', 'C': 'Clover' }
childrens = [ 'Alice',  'Bob', 'Charlie', 'David', 'Eve', 'Fred', 'Ginny',
	      'Harriet', 'Ileana', 'Joseph', 'Kincaid', 'Larry' ]

class Garden(object):
	def __init__(self, windows, **kwargs):
		self.childrens = sorted(kwargs.get('students', None) or childrens)
		self.windows = windows.split()
	
	def plants(self, name):
		result = ''
		for window in self.windows:
			pos = self.childrens.index(name) * 2
			result += window[pos:pos+2]
		return [plants[plant] for plant in result] 
