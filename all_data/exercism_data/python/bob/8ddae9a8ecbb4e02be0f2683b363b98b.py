import re

class Bob(object):
	def __init__(self):
		self.defaultResponse = "Whatever."
		self.silenceResponse = "Fine. Be that way!"
		self.responses = [
			["^\s*$", self.silenceResponse],
			["^[^a-z]+$", "Woah, chill out!"],
			["^.*\?$", "Sure."]]

	def hey(self, whatBobHeard):
		if whatBobHeard == None:
			return self.silenceResponse
		for pattern in self.responses:
			if self.match(pattern[0], whatBobHeard):
				return pattern[1]
		return self.defaultResponse

	def match(self, pattern, string):
		return re.compile(pattern).match(string) != None
