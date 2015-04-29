# -*- coding: utf-8 -*-
from __future__ import unicode_literals

def hey(text):

    if not text.strip():
        return 'Fine. Be that way!'
    elif text.isupper():
        return 'Whoa, chill out!'    
    elif text.strip().endswith('?'):
        return 'Sure.'      
    return 'Whatever.'
