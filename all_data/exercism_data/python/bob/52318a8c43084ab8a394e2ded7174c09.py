import re
class Bob:
	def hey(self,str):
		if(str.strip() == ''):
			return 'Fine. Be that way!'
		if(re.match(r"[A-Z]",str) and re.match(r"[a-z]",str) != None):
			return 'Woah, chill out!'
		if(re.match(r"\?\Z",str)):
			return "Sure."
		
		return "Whatever."
