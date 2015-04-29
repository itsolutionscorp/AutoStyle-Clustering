#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
    #If yelling, i.e. ALLCAPS
    if what.isupper():
        return "Whoa, chill out!"
    #If being asked a question, i.e. ends with '?'
    elif what.endswith('?'):
        return "Sure."
    #If silence, i.e. all whitespace or empty string
    elif what.isspace() or len(what) == 0:
        return "Fine. Be that way!"
    #Anything else!
    else:
        return "Whatever."
