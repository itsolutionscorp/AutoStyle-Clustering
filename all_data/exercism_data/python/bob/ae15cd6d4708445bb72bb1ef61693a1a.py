# -*- coding: utf-8 -*-
""" Implements the rules in the README.md"""
def hey(what):
    """These are the rules for how Bob responds to a sentence"""
    what = what.strip()
    if what.isupper():
        return "Whoa, chill out!"
    elif what.endswith("?"):
        return "Sure."
    elif len(what) == 0:
        return "Fine. Be that way!"
    return "Whatever."
