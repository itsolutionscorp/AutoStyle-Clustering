import re

def hey(what):

    what = what.strip()

    if len(what) == 0:
        answer = u'Fine. Be that way!'
    elif _isyelled(what):
        answer = u'Whoa, chill out!'
    elif what.endswith(u'?'):
        answer = u'Sure.'
    else:
        answer = u'Whatever.'

    return answer

def _isyelled(what):
    letters = u''.join(re.findall(ur'[^\W\d_]', what, re.UNICODE))
    return len(letters) > 0 and letters == letters.upper()
