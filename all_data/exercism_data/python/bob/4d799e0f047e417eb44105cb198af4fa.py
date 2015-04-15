#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
    what = what.strip(' \t\n\r')
    if what.isupper():
        return response.toYell
    elif what.endswith('?'):
        return response.toQuestion 
    elif not what:
        return response.toNothing
    return response.toDefault

class response:
    toYell = 'Whoa, chill out!'
    toQuestion = 'Sure.'
    toNothing = 'Fine. Be that way!'
    toDefault = 'Whatever.'
