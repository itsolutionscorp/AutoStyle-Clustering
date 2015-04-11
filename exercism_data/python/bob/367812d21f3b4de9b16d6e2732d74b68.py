# -*- coding: utf-8 -*-
from __future__ import unicode_literals
import re

"""
Bob is a lackadaisical teenager. In conversation, his responses are very limited.
Bob answers 'Sure.' if you ask him a question.
He answers 'Whoa, chill out!' if you yell at him.
He says 'Fine. Be that way!' if you address him without actually saying anything.
He answers 'Whatever.' to anything else.
"""

def hey(what):

    if what.isupper():
        return 'Whoa, chill out!'
    elif what.strip().endswith('?'):
        return 'Sure.'
    elif re.match(r'^[\s]+$', what) or what == '':
        return 'Fine. Be that way!'
    else:
        return 'Whatever.'
