import random
import string

class Robot(object):
	letter_list = string.letters[26:]
	names_history = []

	def __init__(self):
		self.name = self.generate_random()

	def generate_random(self):
		rand_name = random.choice(self.letter_list) + random.choice(self.letter_list)
		rand_name += str(random.randint(1, 999))
		if rand_name in self.names_history:
			rand_name = self.generate_random()
		self.names_history.append(rand_name)
		return rand_name

	def reset(self):
		self.name = self.generate_random()
