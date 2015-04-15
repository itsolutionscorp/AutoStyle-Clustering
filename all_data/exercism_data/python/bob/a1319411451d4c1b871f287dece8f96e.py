class Bob(object):
	"""This is Bob, the lackadaisical teenager"""
	def hey(self,message):
		if not message or not message.strip():
			return "Fine. Be that way."
		if message == message.upper():
			return "Woah, chill out!"
		if message[-1]=='?':
			return "Sure."
		return "Whatever."
