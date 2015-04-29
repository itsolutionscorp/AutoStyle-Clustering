#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
    if (what.isupper() == True) or (what[-1:].endswith('?') == True and what.isupper() == True):
        return 'Whoa, chill out!'
    elif what.isspace() or what == '':
        return 'Fine. Be that way!'
    elif what[-1:].endswith('?') == True:
        return 'Sure.'
    if what[-1:].endswith('.') == True:
        return 'Whatever.'
    else:
        return 'Whatever.'
