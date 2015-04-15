#Response function for bob.
def hey(what):
		
		#Check if parameter is only space or is empty.
		if what.isspace() or what == '':
			return 'Fine. Be that way!'
		
		#Check if parameter is all uppercase. 		
		elif what.isupper():
			return 'Whoa, chill out!'
		
		#Check if parameter is interrogative.
		elif what.endswith('?'):
			return 'Sure.'
		
		#Check if parameter is declarative.
		elif what.endswith('.'):
			return 'Whatever.'
		
		#Check if parameter is exclamatory.
		elif what.endswith('!'):
			return 'Whatever.'
		
		#I was going to try to strip the parameter of all punctuation and spacing
		#then test whether parameter.isdigit() was true to handle the "1, 2, 3" 
		#example, but I couldn't figure out how to get the function to handle unicode.
		else:
			return 'Whatever.'
