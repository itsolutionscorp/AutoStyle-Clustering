#
# Skeleton file for the Python "Bob" exercise.
#
import re
def isQuestion(what):
    if what[-1:] == "?":
            return True

def isYelling(what):
    what = what.replace("?","")
    what = what.strip()
    if len(what) < 1:
        return False
    for ch in what:
        if ch != ch.upper():
            return False
    return True

def hey(what):
    what = what.strip()
    for x in [","]+list(range(10)):
        what = what.replace(str(x),"")
    if not what:
        return "Fine. Be that way!"
    elif isYelling(what):
        return "Whoa, chill out!"
    elif isQuestion(what):
        return "Sure."
    else:
        return "Whatever."
    return
