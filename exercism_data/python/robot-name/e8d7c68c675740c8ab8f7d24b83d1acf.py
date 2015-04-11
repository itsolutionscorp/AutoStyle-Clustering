import string
import random

names = set()

class Robot(object):

	def __init__(self):
		self.reset()

	def reset(self):

		while True:
			name = ''.join(
				[random.choice(string.ascii_uppercase) for i in range(2)] +
				[random.choice(string.digits) for i in range(3)]
			)

			if name not in names:
				names.add(name)
				break

		self.name = name
