#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
    if what.isupper():
        reply = 'Whoa, chill out!'
    elif len(what.strip()) == 0:
        reply = 'Fine. Be that way!'
    elif what.strip().endswith('?'):
        reply = 'Sure.'
    else:
        reply = 'Whatever.'
    return reply
