#
# Skeleton file for the Python "Bob" exercise.
#

def hey(what):
    if what.isupper():
	return "Whoa, chill out!"   
    elif what.endswith("?"):
	return "Sure."
    elif not what:
	return "Fine. Be that way!" 
    elif not any(i.isalpha() or i.isnumeric() for i in what):
	return "Fine. Be that way!"    
    else:
	return "Whatever."
    return
