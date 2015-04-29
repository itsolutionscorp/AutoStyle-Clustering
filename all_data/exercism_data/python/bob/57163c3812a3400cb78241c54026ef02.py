# -*- coding: utf-8 -*-

import re

def hey(input):
    '''Bob answers 'Sure.' if you ask him a question.
    He answers 'Woah, chill out!' if you yell at him.
    He says 'Fine. Be that way!' if you address him
    without actually saying anything.
    He answers 'Whatever.' to anything else.'''

    if re.search('[a-zA-Z]', input) and input.upper() == input:
        return 'Woah, chill out!'
    if input.endswith('?'):
        return 'Sure.'
    if not re.search('[a-zA-Z0-9]', input):
        return 'Fine. Be that way!'
    else:
        return 'Whatever.'
