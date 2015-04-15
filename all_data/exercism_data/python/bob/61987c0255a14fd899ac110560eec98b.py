#
# Skeleton file for the Python "Bob" exercise.
#

def hey(what):
    what_trimmed = what.strip()
    if len(what_trimmed) == 0:
        return 'Fine. Be that way!'

    response = 'Whatever.'
    if what_trimmed.isupper():
        response = 'Whoa, chill out!'
    elif what_trimmed[len(what_trimmed) - 1] == '?':
        response = 'Sure.'
    return response
