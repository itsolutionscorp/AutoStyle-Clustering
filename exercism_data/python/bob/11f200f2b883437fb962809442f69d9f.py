def is_a_question(what):
    return what and what[len(what) - 1] == '?'


def is_shouting(what):
    return what == what.upper() and what.upper() is not what.lower()


def is_addressing(what):
    return what.strip() == ''


#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
    if is_addressing(what):
        return 'Fine. Be that way!'
    elif is_shouting(what):
        return 'Whoa, chill out!'
    elif is_a_question(what):
        return 'Sure.'
    else:
        return 'Whatever.'
