#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
    what = what.strip()

    if not isinstance(what, unicode):
        return u'Whatever.'
    elif len(what) == 0:
        return u'Fine. Be that way!'
    elif what.isupper():
        return u'Whoa, chill out!'
    elif what[-1] == '?':
        return u'Sure.'
    else:    
        return u'Whatever.'
