# -*- coding: utf-8 -*-
import re

def hey(statement):
    statement = statement.strip()
    if not statement:
        return 'Fine. Be that way!'
    elif re.search(u'[a-zA-ZÜäÄ]', statement) and statement.upper() == statement:
        #This is bad, not sure how to do it better
        return 'Whoa, chill out!'
    elif statement.endswith('?'):
        return 'Sure.'
    else:
        return 'Whatever.'
