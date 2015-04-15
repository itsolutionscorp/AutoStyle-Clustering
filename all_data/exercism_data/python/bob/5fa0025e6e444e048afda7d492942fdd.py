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
    return len(what) == 0

def is_question(what):
    return what and what[-1] == '?'

def is_shouting(what):
    return what.isupper()

def sanitize(what):
    return what.strip()
