from random import choice
from itertools import product

_alphabet='ABCDEFGHIJKLMNOPQRSTUVWXYZ'
_digits='0123456789'

# generates a random name without regard for collisions
def _generate_random_name():
	return choice(_alphabet) + choice(_alphabet) + choice(_digits) + choice(_digits) + choice(_digits)

# generates the next name in sequence
def _generate_name1():
	for name in product(_alphabet,_alphabet,_digits,_digits,_digits):
		yield ''.join(name)
	while True:
		raise ValueError("ran out of names.")

# a named robot
class Robot(object):
	_used_names={}
	_name_generator=_generate_name1()

	def __init__(self):
		self._name=next(Robot._name_generator)

	# generates a random name not previously used
	@classmethod
	def _generate_distinct_name(cls):
		if len(cls._used_names)==len(_alphabet)**2*len(_digits)**3:
			raise ValueError("ran out of names.")
		name=_generate_random_name()
		while name in cls._used_names:
			name=_generate_random_name()
		_used_names.add(name)
		return name

	# a generator wrapper for _generate_distinct_name
	@classmethod
	def _generate_name2(cls):
		while True:
			yield cls._generate_distinct_name()

	# resets the robot's name
	def reset(self):
		self._name=next(Robot._name_generator)
		
	@property
	def name(self):
		return self._name
