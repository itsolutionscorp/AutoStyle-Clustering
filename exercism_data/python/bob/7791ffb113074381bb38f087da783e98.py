import re

def hey(text):
    reply = 'Whatever.'
    asc = ''.join(chr(ord(c) % 128) for c in text)

    if re.match('\s*$', text):
        reply = 'Fine. Be that way!'
    elif (re.match('.*[A-Z]', asc)) and not (re.match('.*[a-z]', asc)):
        reply = 'Whoa, chill out!'
    elif re.match('.*\?$', text):
        reply = 'Sure.'

    return reply
