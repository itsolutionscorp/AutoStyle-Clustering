#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):

    what = sanitize(what)

    if is_silence(what):
        return 'Fine. Be that way!'

    if is_shouting(what):
        return 'Whoa, chill out!'

    if is_question(what):
        return 'Sure.'

    return 'Whatever.'

def is_silence(what):
    return not what

def is_question(what):
    return what.endswith('?')

def is_shouting(what):
    return what.isupper()

def sanitize(what):
    return what.strip()
