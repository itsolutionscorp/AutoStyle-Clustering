#!/usr/bin/env python


def is_shouting(string):

    # A string is marked as a shout if all alphabetic characters are
    # uppercase.  This obviously fails for non-latin and non-cyrillic
    # languages like Chinese, Japanese, Korean, Hindi, and probably
    # Arabic too...  Anyway, strings that do not contain alphabetic
    # characters cannot be classified as shouts.

    is_alpha = False
    for character in string:
        if character.isalpha():
            is_alpha = True
            if character.islower():
                return False

    return is_alpha


def hey(question):
    string = question.strip() if question else ''
    if len(string) == 0:
        return 'Fine. Be that way!'
    elif is_shouting(string):
        return 'Whoa, chill out!'
    elif string[-1] == '?':
        return 'Sure.'
    else:
        return 'Whatever.'

# vim:syn=python:et:
