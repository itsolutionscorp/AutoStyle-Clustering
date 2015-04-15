# Revisions: More readable than the last one, perhaps?


class Bob:

	def __init__(self):
		self.choices = ["Sure.", "Fine. Be that way.", "Woah, chill out!", "Whatever."]

	def hey(self, message):
		return self.test_message(message)

	def test_message(self, message):
		# Question Case
		if message.endswith('?'):
			return self.send_message(0)
		# Empty message
		elif len(message) == 0:
			return self.send_message(1)
		# All caps
		elif message.isupper():
			return self.send_message(2)
		# Everything else
		else:
			return self.send_message(3)

	def send_message(self, index):
		return self.choices[index]
