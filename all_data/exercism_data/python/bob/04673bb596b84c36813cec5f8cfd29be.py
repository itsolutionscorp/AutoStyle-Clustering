#
# Skeleton file for the Python "Bob" exercise.
#

def hey(what):
    what = what.rstrip()
    reply = 'Whatever.'
    if not what:
        reply = 'Fine. Be that way!'
    elif what.isupper():
        reply = 'Whoa, chill out!'
    elif what.endswith('?'):
        reply = 'Sure.'
    return reply
