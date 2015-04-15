import re

def hey(what):
    if not re.search(r'\w',what):
        reply = 'Fine. Be that way!' 
    elif re.search(r'[A-Z]', what) and not re.search(ur'[a-z\u00E0-\u00FF]', what):
        reply = 'Whoa, chill out!'
    elif re.search(r'\?$', what):
        reply = 'Sure.'
    else:
        reply = 'Whatever.'
    return reply
