#
# Skeleton file for the Python "Bob" exercise.
#

responses = {\
    'default':'Whatever.',
    'question':'Sure.',
    'yelling':'Whoa, chill out!',
    'nothing':'Fine. Be that way!'
    }


def hey(what):
    if isNothing(what):
        return responses['nothing']

    if isYelling(what):
       return responses['yelling']

    if isQuestion(what):
        return responses['question']

    return responses['default'] ##This is the only possibility remaining, and therefore does not need a test.



def isYelling(what):
    return what.isupper() ##(yelling signalled by all upper case?)

def isQuestion(what):
    return what.strip().endswith('?')  ##(Questions signaled by a question mark at the end?)

def isNothing(what):
    return not any(c.isalnum() for c in what)##Check for any letters or numbers. if none are present, then it's either whitespace or punctuation
