__author__ = 'js'

import re

def hey(conversation):
    # contains all uppercase letters
    if conversation.isupper():
        return unicode('Whoa, chill out!')
    # contains '?' at the end of line
    elif  re.search(r'\?$', conversation):
        return unicode('Sure.')
    # empty message
    elif not conversation.strip():
        return unicode('Fine. Be that way!')
    # catch all
    else:
        return unicode('Whatever.')
