import random
import string

class Robot:
	def __init__(self):
		self.reset()
		
	def reset(self):
		self.name = ''
		self.name += random.choice(string.ascii_uppercase)
		self.name += random.choice(string.ascii_uppercase)
		self.name += str(random.randint(100, 999))
