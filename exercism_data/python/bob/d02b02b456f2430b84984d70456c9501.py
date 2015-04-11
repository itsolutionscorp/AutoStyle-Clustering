import re

def hey(what):
    return _hey(what.strip())

def _hey(what):

    if len(what) == 0:
        reply = u'Fine. Be that way!'
    elif what.isupper():
        reply = u'Whoa, chill out!'
    elif what.endswith(u'?'):
        reply = u'Sure.'
    else:
        reply = u'Whatever.'

    return reply
