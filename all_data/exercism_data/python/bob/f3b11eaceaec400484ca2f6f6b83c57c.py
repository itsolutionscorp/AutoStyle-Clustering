#!/usr/bin/env python


def hey(message):
    message_in = message.rstrip(' \t\n\r')
    if message_in:
        if message_in.isupper():
            return 'Woah, chill out!'
        elif message_in[-1] == '?':
            return 'Sure.'
        else:
            return 'Whatever.'
    else:
        return 'Fine. Be that way!'
