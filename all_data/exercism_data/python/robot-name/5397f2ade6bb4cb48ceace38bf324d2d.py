from random import randint
from os.path import isfile
# test comment
class Robot (object):
	
	def __init__(self):
		self.reset()
	
	def reset(self):
		self._get_used()
		self.name = self._new_name()
		while self.name in self.used:
			self.name = self._new_name()
		f = file('used.txt', 'a')
		f.write(self.name + '\n')
		f.close()
		
	def _new_name(self):
		return chr(randint(0, 25)+65) + chr(randint(0, 25)+65) + str(randint(0, 9)) + str(randint(0, 9)) + str(randint(0, 9))
		
	def _get_used(self):
		if isfile('used.txt'):
			f = file('used.txt', 'r')
			self.used = [l.strip() for l in f.readlines()]
			f.close() 		
		else:
			self.used = []
