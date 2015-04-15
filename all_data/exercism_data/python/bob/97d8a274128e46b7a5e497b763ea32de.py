#!/usr/bin/env python
# -*- coding: utf-8 -*-

def hey(question):

    if question.isupper():
        return u'Whoa, chill out!'
    if not question or question.isspace():
        return u'Fine. Be that way!'

    if question[-1] == '?':
        return u'Sure.'
    return "Whatever."
