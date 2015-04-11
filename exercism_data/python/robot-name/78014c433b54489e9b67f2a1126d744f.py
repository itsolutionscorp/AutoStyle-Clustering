from random import randint
from os.path import isfile

class Robot (object):
	
	def __init__(self):
		self.reset()
	
	def reset(self):
		self._get_used()
		self.name = self._new_name()
		while self.name in self.used:
			self.name = self._new_name()
		f = open('used.txt', 'a')
		f.write(self.name + '\n')
		f.close()
		
	def _new_name(self):
		return (chr(randint(0, 25)+65) + 
                    chr(randint(0, 25)+65) +
                    "%03d" % (randint(0, 999)) )
		
	def _get_used(self):
		if isfile('used.txt'):
			f = open('used.txt', 'r')
			self.used = [l.strip() for l in f.readlines()]
			f.close() 		
		else:
			self.used = []
