#
# Skeleton file for the Python "Bob" exercise.
#

def is_yell(_input):
    alpha_counter = 0
    for letter in _input:
        if letter.isalpha():
            alpha_counter += 1
            if letter.upper() != letter:
                return False
    if alpha_counter > 0:
        return True
    return False

def is_empty(_input):
    for letter in _input:
        if letter not in ['', ' ', '\t', '\n']:
            return False
    return True

def hey(what):
    reply = ''
    what = what.strip()
    if is_yell(what):
        reply = "Whoa, chill out!"
    elif what.endswith('?'):
        reply = "Sure."
    elif is_empty(what):
        reply = "Fine. Be that way!"
    else:
        reply = "Whatever."
    return reply
