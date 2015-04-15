# Teenager class: class definition for instance of teenager class

class Teenager:

	def __init__(self, name):
		self.name = name

	def hey(self, input):
		if (input.isspace() or input == ''):
			return "Fine. Be that way!"
		elif (input.isupper()):
			return "Woah, chill out!"
		elif (input[len(input)-1] == '?'):
			return "Sure."
		else:
			return "Whatever."
