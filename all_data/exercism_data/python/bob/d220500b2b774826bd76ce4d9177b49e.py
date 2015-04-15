# -*- coding: utf-8 -*-

from __future__ import unicode_literals

def isQuestion(input):
    # if last char is '?', return true unless the string has letters and is a shout.
    if input[-1:] == '?' :
        if input == input.upper() and hasLetters(input):
            return False
        else:
            return True
    else:
        return False

def isShout(input):
    if input == input.upper():
        return True
    else:
        return False

def isNothing(input):
    if input == '':
        return True
    else:
        return False

def hasLetters(input):
    return any(char.isalpha() for char in input)

def hasNumbers(input):
    return any(char.isdigit() for char in input)

def hey(input):
    if isQuestion(input):
        return 'Sure.'
    elif isShout(input) and hasLetters(input):
        return 'Whoa, chill out!'
    elif not hasLetters(input) and not hasNumbers(input):
        return 'Fine. Be that way!'
    else:
        return 'Whatever.'
