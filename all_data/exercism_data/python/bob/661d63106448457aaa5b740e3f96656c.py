# -*- coding: utf-8 -*-

from __future__ import unicode_literals

def hey(phrase):
    if not phrase or phrase.isspace():
        return 'Fine. Be that way!'
    if phrase.isupper():
        return 'Woah, chill out!'
    if phrase.endswith('?'):
        return 'Sure.'
    return 'Whatever.'
