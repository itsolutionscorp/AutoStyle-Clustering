# Skeleton file for the Python "Bob" exercise.
#
def hey(what):

	if (len(what)==0 or what.isspace()): # when nothing's said or the phrase is made of space or the phrase is made of spaces
 		return('Fine. Be that way!')
	else:
			if (what.isupper()): # when you yell at him,phrase is in uppercase
				return('Whoa, chill out!')
			if (what[-1]=="?" or what[-1]==' '): # the phrase is a question, it ends with '? or space and it's not yelled nor a silence'
				return ('Sure.')
		
			else:
				return('Whatever.') # others cases
