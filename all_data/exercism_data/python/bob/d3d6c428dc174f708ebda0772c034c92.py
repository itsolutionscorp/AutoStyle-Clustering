#!/usr/bin/python3.4


def hey(message):
    if message.isupper():
        return 'Whoa, chill out!'
    elif message.endswith('?'):
        return 'Sure.'
    elif message.isspace() or not message:
        return 'Fine. Be that way!'
    else:
        return 'Whatever.'
