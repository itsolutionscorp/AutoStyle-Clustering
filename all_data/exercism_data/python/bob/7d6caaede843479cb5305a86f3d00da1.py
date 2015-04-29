#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):

    response = str(what)

    if response.endswith("!"):
    	return "Whoa, chill out!"

    elif response.endswith("?"):
    	return "Sure."

    elif response == '':
    	return "Fine. Be that way!"

    else:
    	return "Whatever."
