#
# Skeleton file for the Python "Bob" exercise.
#
import string
import re


def hey(what):
    for x in string.whitespace:  # re \s
        what = what.replace(x, "")
    if what == "":
        return "Fine. Be that way!"
    if what == what.upper() and re.search('[a-zA-Z]', what):
        return "Whoa, chill out!"
    if what.endswith("?"):
        return "Sure."

    return "Whatever."
