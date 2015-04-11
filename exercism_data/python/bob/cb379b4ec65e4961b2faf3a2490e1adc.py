#!/usr/bin/env python

# Question: Sure.
# Yell: Whoa, chill out!
# <nil>: Fine. Be that way!
# else: Whatever.

import re
def hasAlpha(s):
    return re.match('.*[^\W\d].*',s, re.UNICODE) is not None
    

def hey(s):

    sprime = s.strip()

    if len(sprime) == 0:
        return "Fine. Be that way!"
    # elif sprime == sprime.upper() and hasAlpha(sprime):
    elif sprime.isupper() :
        return "Whoa, chill out!"
    elif len(sprime) > 1 and sprime[-1] == "?":
        return "Sure."
    else:
        return "Whatever."
