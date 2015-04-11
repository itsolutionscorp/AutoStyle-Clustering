# -*- coding: utf-8 -*-

from __future__ import unicode_literals

def hey(string):
    if len(string.strip()) == 0:
        return 'Fine. Be that way!'
    elif any(string[i].isalpha() for i in range(len(string))) and (
            string.upper() == string):
        return 'Woah, chill out!'
    elif string[-1] == '?':
        return 'Sure.'
    else:
        return 'Whatever.'                 
