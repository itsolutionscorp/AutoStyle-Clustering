#
# Skeleton file for the Python "Bob" exercise.
#
import re

def only_number(text):
    return re.match(r"^[\d\ ]+$", text) is not None

def have_char(text):
    return re.match(r".*[a-zA-Z].*", text) is not None

def saying_nothing(what):
    return len(what) == 0

def is_shouting(what):
    if not have_char(what):
        return False
    what_upper = what.upper()
    return what == what_upper

def is_question(what):
    return what.endswith("?")

def hey(what):
    what = what.strip()
    if saying_nothing(what):
        return "Fine. Be that way!"
    if is_shouting(what):
        return "Whoa, chill out!"
    elif is_question(what):
        return "Sure."
    else:
        return "Whatever."
