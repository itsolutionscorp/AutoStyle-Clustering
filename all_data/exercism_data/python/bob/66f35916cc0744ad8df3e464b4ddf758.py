#!/usr/bin/env python
# -*- coding: UTF-8 -*-

"""
Bob is a lackadaisical teenager.
In conversation, his responses are very limited.

Bob answers 'Sure.' if you ask him a question.

He answers 'Whoa, chill out!' if you yell at him.

He says 'Fine. Be that way!' if you address him
without actually saying anything.

He answers 'Whatever.' to anything else.
"""

__version__ = '0.4.0'
__all__ = ['__version__', 'hey']


EMPTY = lambda s: 'Fine. Be that way!' if not s.strip() else UPPER(s)
UPPER = lambda s: 'Whoa, chill out!' if s.isupper() else QUESTION(s)
QUESTION = lambda s: 'Sure.' if s.endswith('?') else 'Whatever.'

def hey(statement=''):
    """Bob's responses to statements made to him"""
    return EMPTY(statement)
