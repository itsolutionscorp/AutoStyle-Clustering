#
# Skeleton file for the Python "Bob" exercise.
#

def hey(what):
    import re
    
    what = what.strip()    
    whatUpper = what.upper()
    
    if (what == whatUpper) and (re.search('[A-Z]', what) is not None):
        return u'Whoa, chill out!'
    elif what == '':
        return u'Fine. Be that way!'
    elif (len(what) >= 1) and (what[-1] == '?'):
        return u'Sure.'
    else:
        return u'Whatever.'
