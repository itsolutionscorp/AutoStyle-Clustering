#
# Skeleton file for the Python "Bob" exercise.
#
from string import strip
import re

def hey(what):
    what = what.strip()

    if re.match('^.+\?+$', what):
        return "Sure."
    elif re.match('.+!+', what) or re.match('[A-Z]\w+', what):
        return "Whoa, chill out!"
    elif re.match('',''):
        return "Fine. Be that way!"
    else:
        return "whatever"
