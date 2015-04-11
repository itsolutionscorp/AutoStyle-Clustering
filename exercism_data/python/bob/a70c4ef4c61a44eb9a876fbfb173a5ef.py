from __future__ import unicode_literals


def hey(text):
    text = text.strip()
    if text.isupper():
        reply = 'Whoa, chill out!'
    elif text.endswith('?'):
        reply = 'Sure.'
    elif text == '':
        reply = 'Fine. Be that way!'
    else:
        reply = 'Whatever.'
    return reply
