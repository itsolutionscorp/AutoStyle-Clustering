#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
    #Checking for empty or only whitespace
    if not what or what.isspace():
        return('Fine. Be that way!')
    #Let's see if someone is yelling at poor Bob
    if what.isupper():
        return('Whoa, chill out!')
    #Bob doesn't respond well to questions, use some slicing to check this!
    if what[-1]=='?':
        return("Sure.")
    #Otherwise, Bob has a default response
    return('Whatever.')
