#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
    what = what.strip()
    if shout(what):
        return 'Whoa, chill out!'
    if question(what):
        return 'Sure.'
    if nothing(what):
        return 'Fine. Be that way!'
    return 'Whatever.'

def question(what):
    return len(what) > 0 and what[-1] == u'?'

def shout(what):
    return what.isupper()

def nothing(what):
    return what == u''
