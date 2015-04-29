# -*- coding: utf-8 -*-

from __future__ import unicode_literals
import re

def hey(smalltalk):
    hasletters = len(re.findall('[a-zA-Z]', smalltalk)) > 0
    isuppercase = smalltalk.upper() is smalltalk
    issilence = len(re.findall('[\s]', smalltalk)) == len(smalltalk)

    if hasletters and isuppercase:
        return 'Whoa, chill out!'
    elif len(smalltalk) > 0 and smalltalk[-1] == '?':
        return 'Sure.'
    elif issilence or len(smalltalk) == 0:
        return 'Fine. Be that way!'		
    else:
        return 'Whatever.'
