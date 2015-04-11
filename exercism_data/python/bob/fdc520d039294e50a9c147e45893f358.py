#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
    what = what.strip()

    if what and what.isupper():
        response = 'Whoa, chill out!'
    elif what and what[-1] == '?':
        response = 'Sure.'
    elif what == '':
        response = 'Fine. Be that way!'
    else:
        response = 'Whatever.'

    return response
