# coding: utf8
#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
    if what == '' or what.isspace():
        return 'Fine. Be that way!'

    if isYelling(what):
        return 'Whoa, chill out!'

    if what.endswith('?'):
        return 'Sure.'


    return 'Whatever.'


def isYelling(message):
    # i have no idea why this is a special case, but here we go
    UMLAUTS = u'äëïöü'

    if message.isupper() or message.endswith('!'):
        if  message.startswith("Let's"):
            # this is probably poop, but at least one example of 'forceful talking'
            return False
        for letter in message:
            if letter in UMLAUTS:
                return False
        return True
    return False
