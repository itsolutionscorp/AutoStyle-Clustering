#
# Skeleton file for the Python "Bob" exercise.
#


def hey(what):
    modWhat = what.strip(' \a\b\f\n\r\t\v')
    whatLen = len(modWhat)
    print modWhat
    if whatLen == 0:
        return 'Fine. Be that way!'        
    elif (what.isupper()):
        return 'Whoa, chill out!'
    elif (what[-1] == '?'):
        return 'Sure.'
    else:
        return 'Whatever.'
