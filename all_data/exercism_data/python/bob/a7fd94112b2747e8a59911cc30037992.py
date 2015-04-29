#!/usr/bin/python
# -*- coding: utf-8 -*-


def __isupper(c):
    return c in u'ÜÄÖ' or c.isupper()


def hey(msg):
    msg = msg.strip()
    if len(msg) == 0:
        return 'Fine. Be that way!'
    elif (any(c.isalpha() for c in msg)
          and all(not c.isalpha() or __isupper(c) for c in msg)):
        return 'Woah, chill out!'
    elif msg[-1] == '?':
        return 'Sure.'
    else:
        return 'Whatever.'
