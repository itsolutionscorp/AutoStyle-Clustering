from string import ascii_uppercase, digits
from random import choice

class Robot:
	def __init__(self):
		self.reset()
	
	def reset(self):
		l1 = [choice(ascii_uppercase) for n in range(2)]
		l2 = [choice(digits) for n in range(3)]
		l1.extend(l2)
		self.name = "".join(l1)
