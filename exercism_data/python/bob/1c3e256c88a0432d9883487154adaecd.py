# -*- coding: utf-8 -*-

from sys import exit


def hey(text=None):
    if text is None:
        print "You didn't type any text. I'm exiting."
        exit(0)
    elif text.strip() == '':
        return "Fine. Be that way!"
    # isupper() checks only alpha symbols in string
    elif text.isupper():
        return "Whoa, chill out!"
    elif text.strip().endswith('?'):
        return "Sure."
    else:
        return "Whatever."
