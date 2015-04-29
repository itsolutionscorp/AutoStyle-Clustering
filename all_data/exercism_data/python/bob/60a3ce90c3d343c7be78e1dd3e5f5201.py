#!/usr/bin/env python
# -*- coding: utf-8 -*-

def hey(message):
    whatever = "Whatever."
    sure     = "Sure."
    fine     = 'Fine. Be that way!'
    woah     = 'Woah, chill out!'

    # if you address him without actually saying anything
    message_without_ws = ''.join(message.split())
    if message == None or message_without_ws == '':
        return fine

    # shouting
    elif message.isupper():
        return woah

    # a question
    elif message.endswith("?"):
        return sure

    # default case is bob doesn't care
    return whatever
