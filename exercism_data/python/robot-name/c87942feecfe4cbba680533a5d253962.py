import string
from random import randint

all_names = {}

class Robot(object):
	def __init__(self):
		while True:
			new_name = string.ascii_uppercase[ randint(0,25) ] + \
					string.ascii_uppercase[ randint(0,25) ] + \
					str(randint(0,10)) + str(randint(0,10)) + str(randint(0,10))
			if new_name not in all_names:
				self.name = new_name
				all_names[new_name] = True
				break
	def reset(self):
		self.__init__()
