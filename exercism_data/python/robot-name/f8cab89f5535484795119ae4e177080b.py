import string
from random import SystemRandom
random = SystemRandom()

class Robot(object):
	def __init__(self):
		self.reset()

	def reset(self):
		self.name = "".join(random.sample(string.ascii_uppercase, 2) + random.sample("0123456789", 3))
