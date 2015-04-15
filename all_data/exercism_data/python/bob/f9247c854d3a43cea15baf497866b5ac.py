import re
#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
    if re.match('^\s*$',what):
        return "Fine. Be that way!"
    elif what == what.upper() and not what == what.lower():
        return "Woah, chill out!"
    elif what[-1] == "?":
        return "Sure."
    else:
        return "Whatever."

    return
