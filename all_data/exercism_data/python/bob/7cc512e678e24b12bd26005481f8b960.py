# -*- encoding: utf-8 -*-

def hey(message):
    if __is_shout(message):
        return 'Woah, chill out!'
    elif __is_silence(message):
        return 'Fine. Be that way!'
    elif __is_question(message):
        return'Sure.'
    else:
        return 'Whatever.'

def __is_shout(message):
    return message.upper() == message and message.isupper()

def __is_silence(message):
    return message.strip() == ''

def __is_question(message):
    return message[-1] == '?'
