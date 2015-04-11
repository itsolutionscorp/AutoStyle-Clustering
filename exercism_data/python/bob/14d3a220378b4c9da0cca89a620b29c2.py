import re

def hey(what):
    return _hey(_cleanup(what))

def _hey(what):

    if _isempty(what):
        reply = u'Fine. Be that way!'
    elif _isyelled(what):
        reply = u'Whoa, chill out!'
    elif _isquestion(what):
        reply = u'Sure.'
    else:
        reply = u'Whatever.'

    return reply

def _cleanup(what):
    return what.strip()

def _isempty(what):
    return len(what) == 0

def _isyelled(what):
    letters = re.sub(ur'[\W\d_]+', u'', what, flags=re.UNICODE)
    return not _isempty(letters) and letters == letters.upper()

def _isquestion(what):
    return what.endswith(u'?')
