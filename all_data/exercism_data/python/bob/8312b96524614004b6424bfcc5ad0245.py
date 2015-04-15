#!/usr/bin/env python

def isYelling(message):
    return message.isupper()

def isAskingQuestion(message):
    try:
        if '?' in message[-1]:
            return True
        return False
    except IndexError:
        return False

def isSilence(message):
    if message.strip() is "":
        return True
    return False

def hey(message):
    response = 'Whatever.'
    if isYelling(message):
        response = 'Whoa, chill out!'
    elif isAskingQuestion(message):
        response = 'Sure.'
    elif isSilence(message):
        response = 'Fine. Be that way!'
    return response
