import random

class Robot(object):
	def __init__(self):
		self.name = self.get_name()
		
	#@classmethod	
	def get_name(self):
		char = ''.join(random.choice('ABCDEFGHIJKLMNOPQRSTUVWXYZ') for i in range(2))
		num = ''.join(random.choice('0123456789') for i in range(3))
		return char+num

	def reset(self):
		self.name=self.get_name()
