# -*- coding: utf-8 -*-

from __future__ import unicode_literals


def is_yelling(string_input):
    '''
    Return True if the entire string is uppercase
    '''
    return string_input.isupper()


def is_empty(string_input):
    '''
    Return true if a string is empty (all whitespace)
    '''
    return not string_input.strip()


def is_question(string_input):
    '''
    Returns True if the string input ends with a questionmark
    '''
    return string_input[-1] == '?'


def hey(sentence):
    '''
    Function to say hey to Bob.

    The one unresolved issue is priority for yelling vs asking a
    question.

    What if someone would YELL a question? Should
    Bob say "Woah, chill out!" or should Bob say "Sure."?
    '''

    def f(x):
        return {

        }.get(x, 'Whatever.')

    if is_empty(sentence):
        return 'Fine. Be that way!'

    elif is_yelling(sentence):
        return 'Woah, chill out!'

    elif is_question(sentence):
        return 'Sure.'

    else:
        return 'Whatever.'
