#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
    ''' Bob responds according to README.md when spoken to
    '''

    if not isinstance(what, basestring):
        return 'Whatever.'

    if isSilence(what):
        return 'Fine. Be that way!'
    elif isYell(what):
        return 'Whoa, chill out!'
    elif isQuestion(what):
        return 'Sure.'
    else:
        return 'Whatever.'

def isSilence(what):
    return what.strip() == ""

def isYell(what):
    return what.isupper()

def isQuestion(what):
    return what.strip().endswith('?')
