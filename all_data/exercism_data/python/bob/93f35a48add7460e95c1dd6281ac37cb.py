#
# Skeleton file for the Python "Bob" exercise.
#

import re

# matches if string ends in more than one '!'
shout_re = re.compile(r".*!{2,}$")

def is_shouting(what):
    contains_uppercase_letters = False
    for i in what:
        if i.islower():
            return shout_re.match(what)
        elif i.isupper():
            contains_uppercase_letters = True
    return contains_uppercase_letters

def hey(what):
    what = what.strip()
    if (what == ""):
        return "Fine. Be that way!"
    if is_shouting(what):
        return "Whoa, chill out!"
    if (what[-1] == "?"):
        return "Sure."
    return "Whatever."
