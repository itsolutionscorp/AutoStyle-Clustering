#
# Skeleton file for the Python "Bob" exercise.
#


def hey(what):
    if isYelling(what):
        return 'Whoa, chill out!'
    if isQuestion(what):
        return 'Sure.'
    if isEmpty(what):
        return 'Fine. Be that way!'
    return 'Whatever.'


def isQuestion(what):
    return what.rstrip().endswith('?')


def isYelling(what):
    return what.isupper()


def isEmpty(what):
    return not what.strip()
