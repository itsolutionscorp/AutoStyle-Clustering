#!/usr/bin/env python

from __future__ import division, print_function, unicode_literals 


def hey(str=None):
    try:
        if not str:
            return "Fine. Be that way!"
        elif str.isupper():
            return "Whoa, chill out!"
        elif str.endswith('?'):
            return "Sure."
        elif str.isspace():
            return "Fine. Be that way!"
        else:
            return "Whatever."
    except TypeError as e:
        return "Fine. Be that way!"
    except AttributeError as e:
        return "Fine. Be that way!"


if __name__ == '__main__':
    print(hey("How are you today?"))
    print(hey("WHAT IS THAT?!?!?!?"))
    print(hey("Howdy"))
    print(hey(42))
