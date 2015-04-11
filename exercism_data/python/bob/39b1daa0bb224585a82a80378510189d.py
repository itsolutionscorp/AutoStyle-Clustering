#
# Skeleton file for the Python "Bob" exercise.
#


def hey(what):
    last_character = what.strip()[-1:]
    response = 'Whatever.'
    if what.isupper():
        response = 'Whoa, chill out!'
    elif last_character == '?':
        response = 'Sure.'
    elif last_character == '':
        response = 'Fine. Be that way!'
    return response
