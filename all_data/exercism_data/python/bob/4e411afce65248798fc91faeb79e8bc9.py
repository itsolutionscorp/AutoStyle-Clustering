# -*- encoding: utf-8 -*-

def hey(message):
    if __shout(message):
        return 'Woah, chill out!'
    elif __silence(message):
        return 'Fine. Be that way!'
    elif __question(message):
        return'Sure.'
    else:
        return 'Whatever.'

def __shout(message):
    return message.upper() == message and message.isupper()

def __silence(message):
    return message.strip() == ''

def __question(message):
    return message[-1] == '?'
