#!/usr/bin/env python
# -*- coding: utf-8 -*-
# Skeleton file for the Python "Bob" exercise.
from __future__ import unicode_literals
import re

def hey(what):
    if what.endswith('?') and not what.isupper():
        answer = 'Sure.'
    elif what.isupper():
        answer = 'Whoa, chill out!'
    elif re.match('\s*$',what):
        answer = 'Fine. Be that way!'
    else:
        answer = 'Whatever.'

    return answer
