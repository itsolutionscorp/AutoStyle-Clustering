#
# Skeleton file for the Python "Bob" exercise.
#

from __future__ import unicode_literals

def hey(what):
    what = preprocess(what)
    if is_asking(what) and not is_shouting(what):
        return "Sure."
    if is_shouting(what):
        return "Whoa, chill out!"
    if is_blanking(what):
        return "Fine. Be that way!"
    return "Whatever."

def preprocess(what):
    assert isinstance(what, str)
    # filters out characters not visible to Bob (str.isprintable() is new in Python 3.something)
    what = "".join([char for char in what if char.isprintable()])
    while "  " in what:
        what = what.replace("  ", " ")
    what = what.rstrip()
    return what

def is_shouting(what):
    return what.upper() == what and what.upper() != what.lower()

def is_asking(what):
    return what.endswith("?")

def is_blanking(what):
    return what == ""
