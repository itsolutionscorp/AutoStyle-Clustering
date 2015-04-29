# -*- coding: utf-8 -*-

from __future__ import unicode_literals

def hey(g):
    if g.isupper():
        return "Whoa, chill out!"
    elif g[-1:] == "?":
        return "Sure."
    elif g.isspace():
        return "Fine. Be that way!"
    elif g[0:1] == " ":
        return "Whatever."
    elif len(g) == 0:
        return "Fine. Be that way!"
    else:
        return "Whatever."
