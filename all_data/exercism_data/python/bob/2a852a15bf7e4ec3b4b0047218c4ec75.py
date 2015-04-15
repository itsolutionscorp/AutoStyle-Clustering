#!/usr/bin/env python
#coding: utf-8 -*-
"""Bob is sad because he does not pass the turing test."""

from __future__ import unicode_literals
import re

RE_NUMBER = re.compile(r'^.*\d+')
RE_FULLSTOP = re.compile(r'^.*\.\s*$')
RE_QUESTIONMARK = re.compile(r'^.*\?\s*$')
RE_EXCLAMATIONMARK = re.compile(r'^.*\!\s*$')
RE_HAS_LOWERCASE = re.compile(ur'^.*[a-züäö]')
RE_HAS_UPPERCASE = re.compile(ur'^.*[A-ZÖÄÜ]')
RE_WHITESPACE = re.compile(r'^\s*$')

ANSWER_WHATEVER = 'Whatever.'
ANSWER_CHILL = 'Whoa, chill out!'
ANSWER_AFFIRM = 'Sure.'
ANSWER_FINE = 'Fine. Be that way!'

def hey(what):
    """Parse a given greeting and return an answer accordingly."""
    if RE_WHITESPACE.match(what):
        return ANSWER_FINE

    if RE_HAS_LOWERCASE.match(what) is None and \
        RE_HAS_UPPERCASE.match(what):
        return ANSWER_CHILL

    if RE_FULLSTOP.match(what):
        return ANSWER_WHATEVER

    if RE_EXCLAMATIONMARK.match(what):
        return ANSWER_WHATEVER

    if RE_QUESTIONMARK.match(what):
        return ANSWER_AFFIRM

    return ANSWER_WHATEVER
