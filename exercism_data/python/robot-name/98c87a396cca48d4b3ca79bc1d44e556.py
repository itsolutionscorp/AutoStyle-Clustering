import random
import string

class Robot:

	def __init__(self):
		self.name = Robot.getName()

	def getName():
		name = "".join(random.choice(string.ascii_uppercase) for i in range(2))
		name = name + "".join(random.choice(string.digits) for i in range(3))
		return(name)

	def reset(self):
		self.name = Robot.getName()
