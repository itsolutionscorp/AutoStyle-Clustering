import re
class Bob:
	def hey(self,str):
		if(str.strip() == ''):
			return 'Fine. Be that way!'
		if(re.search(r"[A-Z]",str) and re.search(r"[a-z]",str) != None):
			return 'Woah, chill out!'
		if(re.search(r"\?$",str)):
			return "Sure."
		return "Whatever."
