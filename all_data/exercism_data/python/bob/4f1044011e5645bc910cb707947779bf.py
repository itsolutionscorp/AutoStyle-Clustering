#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
    # if all of the alpha charachters are upper case then its yelling
    if what.isupper():
	return 'Whoa, chill out!' 
    # if the last charachter besides whitespace/escaped is a question mark then its a question
    elif what.rstrip().endswith('?'):
        return 'Sure.'
    # if all of the charchaters are not alphanumeric then its a blank statement
    elif not any(char.isalnum() for char in what):
	return 'Fine. Be that way!'
    # anything else
    else:
        return 'Whatever.'
