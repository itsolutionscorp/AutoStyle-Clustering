#
# Skeleton file for the Python "Bob" exercise.
#
def is_question(what):
    if what.endswith('?'):
        return True

    return False


def is_yelling(what):
    if what.isupper():
        return True


def hey(what):
    # Strip whitespaces and tab.
    what = what.strip()

    # Was bob addressed without saying anything?
    if len(what) == 0:
        return 'Fine. Be that way!'

    # Was bob yelled at?
    elif is_yelling(what):
        return 'Whoa, chill out!'

    # Was bob been asked a question?
    elif is_question(what):
        return 'Sure.'
    else:
        return 'Whatever.'
