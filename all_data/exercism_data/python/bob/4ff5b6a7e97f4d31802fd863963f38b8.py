#
# Skeleton file for the Python "Bob" exercise.
#
import re

def hey(what):
   
    if what.isupper():
        return "Whoa, chill out!"
    elif what.endswith("?"):
        return "Sure."
    elif what.isspace():
        return "Fine. Be that way!"
    elif what == "":
        return "Fine. Be that way!"
    else:
        return "Whatever."
