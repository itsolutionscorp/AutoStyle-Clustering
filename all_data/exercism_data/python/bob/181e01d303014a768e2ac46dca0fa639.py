#
# Skeleton file for the Python "Bob" exercise.
#
from __future__ import print_function
import re

def hey(what):
    if what.isupper():
        return 'Whoa, chill out!'
    
    if what.strip().endswith("?"):
        return "Sure."

    if what.strip() == "":
        return "Fine. Be that way!"

    return "Whatever."
