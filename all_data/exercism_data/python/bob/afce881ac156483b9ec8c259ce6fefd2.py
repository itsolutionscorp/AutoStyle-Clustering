def hey(what):
    if isEmpty(what):
        return RESPONSE_TO_EMPTY
    if isShout(what):
        return RESPONSE_TO_SHOUT
    if isQuestion(what):
        return RESPONSE_TO_QUESTION
    return RESPONSE_DEFAULT


def isQuestion(what):
    check = what.strip()
    return ("?" in check) and check.endswith("?")


def isShout(what):
    check = what.strip()
    return not (len([x for x in check if x.islower()]) > 0) and \
        len([y for y in check if y.isupper()]) > 0


def isEmpty(what):
    return what is None or len(what.strip()) == 0


RESPONSE_TO_EMPTY = "Fine. Be that way!"
RESPONSE_TO_QUESTION = "Sure."
RESPONSE_TO_SHOUT = "Whoa, chill out!"
RESPONSE_DEFAULT = "Whatever."
