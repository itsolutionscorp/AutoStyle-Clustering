#
# Skeleton file for the Python "Bob" exercise.
#

import re

HAS_UPPER_LETTERS = re.compile(".+?[A-Z]+")

def hey(what):
    what = what.strip()
    
    if not what:
        return "Fine. Be that way!"

    if what.upper() == what and HAS_UPPER_LETTERS.match(what):
        return "Whoa, chill out!"
    
    if what.endswith("?"):
        return "Sure."
   
    return "Whatever."
