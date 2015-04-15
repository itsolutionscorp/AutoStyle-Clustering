import re

def hey(what):

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

    return _hey(what.strip())
