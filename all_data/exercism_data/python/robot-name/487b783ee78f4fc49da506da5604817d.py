import random

def _rand_letter():
	return chr(random.randint(ord('A'), ord('Z')))

def _rand_digit():
	return str(random.randint(0, 9))

class Robot(object):
	GENERATED_NAMES = set()

	def __init__(self):
		self._generate_name()

	def reset(self):
		self._generate_name()

	def _generate_name(self):
		self.name = _rand_letter() + _rand_letter() + _rand_digit() \
			+ _rand_digit() + _rand_digit()
		if self.name in Robot.GENERATED_NAMES:
			self._generate_name()
		else:
			Robot.GENERATED_NAMES.add(self.name)
