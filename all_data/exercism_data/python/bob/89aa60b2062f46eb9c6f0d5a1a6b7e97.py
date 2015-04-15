#!/usr/bin/env python
# -*- coding: utf-8 -*-
"""
Bob is a lackadaisical teenager. In conversation, his responses are very
limited.

Bob answers 'Sure.' if you ask him a question.

He answers 'Whoa, chill out!' if you yell at him.

He says 'Fine. Be that way!' if you address him without actually saying
anything.

He answers 'Whatever.' to anything else.
"""


def hey(phrase):
    """Given 'phrase', returns Bob's answer"""
    
    if phrase.isupper():
        return "Whoa, chill out!"
    if phrase[-1:] == "?":
        return "Sure."
    if phrase == "" or phrase.isspace():
        return "Fine. Be that way!"
    return "Whatever."
