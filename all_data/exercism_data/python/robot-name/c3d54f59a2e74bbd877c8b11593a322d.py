from itertools import combinations_with_replacement
from random import choice
from string import ascii_uppercase
numbers = "0123456789"

used_names = []
letters = [''.join(c) for c in combinations_with_replacement(ascii_uppercase, 2)]
free_names = [prefix + str(i).rjust(3, '0') for prefix in letters for i in range(1000)]

class Robot(object):
	def __init__(self):
		self.set_random_name()

	def reset(self):
		old_name = self.name
		self.set_random_name()
		free_names.append(old_name)

	def set_random_name(self):
		self.name = choice(free_names)
		free_names.remove(self.name)
		used_names.append(self.name)
