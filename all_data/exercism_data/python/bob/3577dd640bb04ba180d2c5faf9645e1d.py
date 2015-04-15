#
# Skeleton file for the Python "Bob" exercise.
#

def hey(what):
    """Main function for the Bob chatbot"""
    #First, we'll do a check for a non-string, the empty string, or a long set of blank space
    if (type(what) is not str) or (what.strip() == ''):
        return "Fine. Be that way!"
    #If the input is a valid one, we'll check if Bob is being yelled at - assuming all caps means being yelled at.
    elif (what.isupper()):        
        return 'Whoa, chill out!'
    #If the input wasn't yelling at Bob, we'll check to see if it asked a question
    elif (what.strip()[-1] == '?'): 
        return "Sure."
    #Finally, if none of the above are the case, we'll return the default value
    else:
        return 'Whatever.'
