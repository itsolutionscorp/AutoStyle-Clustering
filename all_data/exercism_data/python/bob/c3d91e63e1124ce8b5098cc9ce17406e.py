#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
    what = what.strip()                                     # Remove leading and trailing whitespace from input
    if not what:                                            # Test if the input is empty. Do this first, or won't evaluate.
        return "Fine. Be that way!" 
    elif (what[-1] == "?") and (what.isupper() == False):   # Test if it's a question, and also not uppercase
        return "Sure."
    elif what.isupper():                                    # Test if it's uppercase
        return "Whoa, chill out!"
    else:                                                   # Whatever to anything else
        return "Whatever."
