import re
class Bob:
	def hey(self,str):
		'''
		if(str.strip() == ''):
			return 'Fine. Be that way!'
		if(re.match(r"[A-Z]",str) and re.match(r"[a-z]",str) != None):
			return 'Woah, chill out!'
		if(re.match(r"\?\Z",str)):
			return "Sure."
		return "Whatever."
		'''
		if not str.strip():
			return "Fine. Be that way!"
		# Then test if Bob is shouted at
		elif str.isupper():
			return "Woah, chill out!"
		# Detect questions
		elif str.endswith("?"):
			return "Sure."
		# At last handle everthing else if nothing from above applies
		else:
			return "Whatever."
