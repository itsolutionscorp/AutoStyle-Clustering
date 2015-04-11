#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):

    what = what.strip()                     # Remove leading and trailing whitespace from input
    
    if not what:                            # Test if the input is empty. Do this first, or won't evaluate.
        return "Fine. Be that way!" 
    elif what.isupper():                    # Test if it's uppercase
        return "Whoa, chill out!"
    elif (what[-1] == "?"):                 # Test if it's a question
        return "Sure."
    else:                                   # Whatever to anything else
        return "Whatever."
