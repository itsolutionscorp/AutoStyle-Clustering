#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
    if ( len(what.strip()) == 0 ) : 
        return 'Fine. Be that way!'
    if ( what.isupper()):
        return 'Whoa, chill out!'
    if ('?' in what.strip()[-1] ):
        return 'Sure.'
    else:
        return 'Whatever.'
