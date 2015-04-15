#
# Skeleton file for the Python "Bob" exercise.
#
chill_response='Whoa, chill out!'
sure_response='Sure.'
fine_response='Fine. Be that way!'
whatever_response='Whatever.'

def hey(what):
    what = what.strip(' \t\n\r')
    if what.isupper():
        return chill_response
    elif what.endswith('?'):
        return sure_response
    elif not what:
        return fine_response
    return whatever_response
