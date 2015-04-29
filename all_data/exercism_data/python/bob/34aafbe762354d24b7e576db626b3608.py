# -*- coding: utf-8 -*-

import re

def hey(string):
    string = string.strip()
    if len(string) > 0 and re.search('[a-zA-Z]', string):
        if string.upper() == string:
            return 'Woah, chill out!'
    if string.endswith('?'): return 'Sure.'   
    elif len(string) == 0: return 'Fine. Be that way!'
    return 'Whatever.'
    
