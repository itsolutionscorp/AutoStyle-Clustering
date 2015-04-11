class Bob:

	def __init__(self):
		self.chill = "Woah, chill out!"
		self.whatever = "Whatever."
		self.sure = "Sure."
		self.fine = "Fine. Be that way!"

	def hey(self, s):
		if s.strip() == '':
			return self.fine
		elif s.isupper():
			return self.chill
		elif s[-1] == '?':
			return self.sure
		else:
			return self.whatever
