import re

def hey(text):
    reply = 'Whatever.'

    if re.match('\s*$', text):
        reply = 'Fine. Be that way!'
    elif text.isupper():
        reply = 'Whoa, chill out!'
    elif re.match('.*\?$', text):
        reply = 'Sure.'

    return reply
