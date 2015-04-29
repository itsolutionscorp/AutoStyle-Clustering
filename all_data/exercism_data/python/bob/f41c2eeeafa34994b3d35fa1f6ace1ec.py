#
# Skeleton file for the Python "Bob" exercise.
#
import re
defensive_score = 0

#
# Primary Function
#


def hey(what):
    if (is_defensive(what) == True):
        what = "Whoa, chill out!"
    elif (is_passive(what) == True):
        what = "Sure."
    elif (is_apathetic(what) == True):
        what = "Whatever."
    else:
        # The expression, "Fine. Be that way!" is the generic response.
        what = "Fine. Be that way!"
    return what

#
# Whoa, chill out!
#


def is_defensive(what):
    matchCAPS = re.findall('([A-Z][A-Z][A-Z]+)', what, flags=0)
    for i in matchCAPS:
        if matchCAPS:
            return True
        else:
            return False


#
# Sure.
#
def is_passive(what):
    matchQuestions = re.findall('\?$', what, flags=0)
    for match in matchQuestions:
        if match:
            return True
        else:
            return False

#
# Fine, Be that way!
#


def is_incredulous(what):
    matchNonsense = re.findall('^[\w-]+$', what, flags=0)
    matchEscapes = re.findall("\\.", what, flags=0)
    for i in matchEscapes:
        if i:
            return True
        else:
            return False

#
# Whatever
#


def is_apathetic(what):
    matchPropersentence = re.findall('^[A-Z]\w', what, flags=0)
    for i in matchPropersentence:
        if i:
            return True
        else:
            return False
