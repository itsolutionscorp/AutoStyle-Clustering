#
# Skeleton file for the Python "Bob" exercise.
#

def is_question(what):
    return what.endswith('?')

def is_yell(what):
    return what.isupper()

def is_slient(what):
    return len(what.strip()) == 0

def hey(what):

    if is_slient(what):
        answer = 'Fine. Be that way!'
    elif is_yell(what):
        answer = 'Whoa, chill out!'
    elif is_question(what):
        answer = 'Sure.'
    else:
        answer = 'Whatever.'

    return answer
