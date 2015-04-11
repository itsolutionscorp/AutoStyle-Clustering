#input string
#output string 
#accept a statement from the user and return a response based on the statement given
def hey(statement):

	#remove whitespace from the input
	statement = statement.strip()
	
	#Yelling
	if statement.isupper():
		return 'Woah, chill out!'
	#Question
	elif statement.endswith('?'):
		return 'Sure.'
	#Check if statement is empty
	elif not statement:
		return 'Fine. Be that way!'
	#For everything else
	else:
		return 'Whatever.'
	


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
	
