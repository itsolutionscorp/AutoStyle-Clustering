#
# Skeleton file for the Python "Bob" exercise.
#
import re

def hey(what):
    if re.search('[a-zA-Z0-9]+', what):
        if re.search('[a-zA-Z]+', what) and what.isupper():
            return "Whoa, chill out!"
        if what[-1] == "?":
            return "Sure."
        else:
            return "Whatever."
    else:
        return "Fine. Be that way!"
