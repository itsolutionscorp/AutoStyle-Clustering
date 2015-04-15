from string import upper
import re

CHILL = "Woah, chill out!"
SURE = "Sure."
EMPTY = "Fine. Be that way!"
WHATEVER = "Whatever."

ASCII_STRING = re.search(r"[a-zA-Z]", msg)
WHITE_SPACE_ONLY = not re.search(r"\S", msg)


def hey(msg):
    if len(msg) == 0 or WHITE_SPACE_ONLY:
        return EMPTY
    elif msg == upper(msg) and ASCII_STRING:
        return CHILL
    elif msg[-1] == '?':
        return SURE
    if not re.search(r"[a-zA-Z]", msg):
        return WHATEVER
    else:
        return WHATEVER
