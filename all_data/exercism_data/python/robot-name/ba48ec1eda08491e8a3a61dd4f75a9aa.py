import string
import random

robots = []

class Robot():
		
	def __init__(self):
		self.name = self.new_robot()
		robots.append(self.name)

	def new_robot(self, size = 5):
		name = ''
		for i in range(size):
			if i<=1:
				name += random.choice(string.ascii_uppercase)
			else:
				name += random.choice(string.digits)

		if name not in robots:
			return name
		else:
			return self.new_robot()

	def reset(self):
		robots.remove(self.name)
		self.name = self.new_robot()
