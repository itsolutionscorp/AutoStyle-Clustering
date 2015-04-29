class Bob:
	def hey(self, arg):
		if arg.isupper():
			return "Woah, chill out!"
		elif arg == '' or arg.isspace():
			return "Fine. Be that way!"	
		elif arg.endswith("?"):
			return "Sure."	
		else:
			return "Whatever."
		
	
