import re
class Bob:
	def hey(self,str):
		if not str.strip():
			return 'Fine. Be that way!'
		if(re.search(r"[A-Z]",str) and not re.search(r"[a-z]",str)):
			return 'Woah, chill out!'
		if re.search(r"\?$",str):
			return "Sure."
		return "Whatever."
