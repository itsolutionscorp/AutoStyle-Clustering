# -*- coding: utf-8 -*-
from __future__ import unicode_literals


def hey(question):
    if question.isupper():
        return "Whoa, chill out!"
    elif question[-1:] == "?":
        return "Sure."
    elif len(question.strip()) == 0:
        return "Fine. Be that way!"
    else:
        return "Whatever."
