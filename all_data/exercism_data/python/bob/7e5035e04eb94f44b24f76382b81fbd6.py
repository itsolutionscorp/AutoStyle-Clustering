# -*- coding: utf-8 -*-

def hey(phrase):
    # Do not assume phrase is a string
    phrase = unicode(phrase).strip()
    if not phrase:
        return 'Fine. Be that way!'
    if phrase.isupper():
        return 'Woah, chill out!'
    if phrase.endswith('?'):
        return 'Sure.'
    return 'Whatever.'
