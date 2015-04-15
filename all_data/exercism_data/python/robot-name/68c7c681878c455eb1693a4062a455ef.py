import random


class Names(object):
	def __init__(self):
		self.names=[]

n=Names()
		
class Robot(object):
	
	def __init__(self, names=n):
		self.names=names.names
		self.give_name()
	
	def give_name(self):
		letters = 'ABCDEFGHIJKLMNOPQRSTUVWXYZ'
		name = ''
		while name == '':
			new = ''.join([random.choice(letters) for i in range(2)])+''.join(["%s" % random.randint(0,9) for x in range(3)])
			if new not in self.names:
				name = new
				self.name=name
		self.names.append(self.name)
		
	def reset(self):
		self.give_name()
