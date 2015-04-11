class Bob:
	def hey(self, ask):
		conversation = Identify(ask)

		if conversation.question():
			return "Sure."
		elif  conversation.yell():
			return "Woah, chill out!"
		elif conversation.anything():
			return "Fine. Be that way!"
		else:
			return "Whatever."

class Identify:
	def __init__(self, ask):
		self.ask = ask or ""

	def question(self):
		return self.ask.endswith("?")

	def yell(self):
		return self.ask == self.ask.upper()

	def anything(self):
		return not self.ask.strip()
