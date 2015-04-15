import re

def hey(what):
    if not re.search(r'\w',what):
        reply = 'Fine. Be that way!' 
    elif re.search('[A-Z]', what) and not re.search('[a-z]', what):
        reply = 'Whoa, chill out!'
    elif re.search(r'\?$', what):
        reply = 'Sure.'
    else:
        reply = 'Whatever.'
    return reply
