class bob():

	def hey(self, arg):
		if arg[len(arg)-1] == '?':
			return "Sure."
		elif arg.upper() == arg:
			return "Whoa, chill out!"
		#Checks empty string.
		elif not arg.strip():
			return 'Fine. Be that way!'
		else:
			return "Whatever."
