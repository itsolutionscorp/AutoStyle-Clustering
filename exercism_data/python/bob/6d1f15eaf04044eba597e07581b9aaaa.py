class Bob():
	def hey(self, msg):
		if(msg.isupper()):
			return "Woah, chill out!"		
		elif(msg.endswith("?")):
			return "Sure."
		if(len(msg.strip()) == 0):
			return "Fine. Be that way!"
		return "Whatever."
