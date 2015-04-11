import re

class Bob:
	def hey(self, text):
		if(text.strip() == ""):
			return "Fine. Be that way!"
		if(text.upper() == text and re.findall("[a-zA-Z]", text)):
			return "Woah, chill out!"
		if(text[-1:] == "?"):
			return "Sure."
		return "Whatever."
