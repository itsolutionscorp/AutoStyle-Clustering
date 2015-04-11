#!/usr/bin/python
# -*- coding: utf8 -*-
#
# Skeleton file for the Python "Bob" exercise.
#


def hey(sentence):

    sentence = sentence.strip()
    # nothing
    if not sentence:
        return 'Fine. Be that way!'
    # shouting
    elif sentence.isupper():
        return 'Whoa, chill out!'
    # question
    elif sentence.endswith('?'):
        return 'Sure.'
    # rest
    else:
        return 'Whatever.'
