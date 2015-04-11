import re

def hey(msg):

    msg = msg.strip()

    if not msg:
        return 'Fine. Be that way!'
    elif msg.isupper():
        return 'Woah, chill out!'
    elif msg.endswith('?'):
        return 'Sure.'
    else:
        return 'Whatever.'
