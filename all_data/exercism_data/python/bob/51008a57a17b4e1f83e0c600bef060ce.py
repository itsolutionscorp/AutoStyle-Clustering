#input string
#output string 
#accept a statement from the user and return a response based on the statement given
def hey(statement):

	#create a dictionary with all of Bob's responses.
	response_dict = {
						0:'Whatever.',
						1:'Woah, chill out!',
						2:'Sure.',
						3:'Fine. Be that way!',
					}
	#Let the spaghetti code begin!
	
	#make sure something has been entered
	if len(statement):
	
			#get whatever punctuation is being used in the sentence
			punctuation = statement[-1]
			
			#check for whitespace, tabs, newline, etc
			if statement.isspace():
					return response_dict[3]
			#check to see if the statement ends in a period
			elif punctuation == '.':
					return response_dict[0]
			#check if the statement is all uppercase
			elif statement.isupper():
					return response_dict[1]
			#check if the statement ends in an exclamation point and is uppercase.
			elif (punctuation == '!') and (statement.isupper()):
					return response_dict[1]
			#check to see if the statement ends in a question mark
			elif punctuation == '?':
					return response_dict[2]
			#All other cases return 'Whatever.'
			else:
					return response_dict[0]
	else:
			return response_dict[3]
	


# This main function is for testing purposes only
#def main():

#	print "Please enter your statement:"
#	print
#	statement = raw_input()
	
#	output = hey(statement)
#	
#	print output
#	print
	
#main()
	
