class bob:
	@classmethod
	def hey(self,text):
		if(not text.strip()):
			return "Fine. Be that way!"
		elif(text.isupper()):
			return "Whoa, chill out!"
		elif(text[-1] == "?"):
			return "Sure."
		else:
			return "Whatever."