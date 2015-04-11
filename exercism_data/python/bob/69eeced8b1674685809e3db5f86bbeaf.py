# -*- coding: utf-8 -*-

__author__ = 'James'

def hey(message):
    """
    Say something to Bob, the typical teenbot.
    :param message: Message spoken to Bob.
    :return: Bob's response.
    """

    #Remove whitespace from message.
    message = message.strip()

    #Checks
    if not message:
        return 'Fine. Be that way!'
    elif message.isupper():
        return 'Woah, chill out!'
    elif message.endswith('?'):
        return 'Sure.'
    else:
        return 'Whatever.'
