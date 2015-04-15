#!/usr/bin/env python

def isYelling(message):
    return message.isupper()

def isAskingQuestion(message):
    return message.endswith('?')

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
