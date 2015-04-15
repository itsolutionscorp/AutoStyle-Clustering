class Bob:
	def hey(self, ask):
		conversation = Identify(ask)

		if conversation.anything():
			return "Fine. Be that way!"
		elif  conversation.yell():
			return "Woah, chill out!"
		elif conversation.question():
			return "Sure."
		else:
			return "Whatever."

class Identify:
	def __init__(self, ask):
		self.ask = ask or ""

	def question(self):
		return self.ask.endswith("?")

	def yell(self):
		return self.ask.isupper()

	def anything(self):
		return not self.ask.strip()
