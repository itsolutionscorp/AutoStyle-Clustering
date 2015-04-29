#Response function for bob.
def hey(what):
		
		#Check if parameter is only space or is empty.
		if what.isspace() or what == '':
			return 'Fine. Be that way!'
		
		#Check if parameter is all uppercase. 		
		elif what.isupper():
			return 'Whoa, chill out!'
		
		#Check if parameter is interrogative.
		elif what.strip().endswith('?'):
			return 'Sure.'
		
		#Check if parameter is declarative or exclamatory.
		elif what.strip().endswith('.') or what.strip().endswith('!'):
			return 'Whatever.'
		
		#I could not come up with a test that removes punctuation and then evaluates
		#isdigit(), so 'Whatever.' is being returned here to deal with the "1, 2, 3" test
		#case
		else:
			return 'Whatever.'
