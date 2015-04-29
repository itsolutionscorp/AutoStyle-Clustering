#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
    what = what.strip()
    if what.isupper():
        reply = 'Whoa, chill out!'
    elif len(what) == 0:
        reply = 'Fine. Be that way!'
    elif what.endswith('?'):
        reply = 'Sure.'
    else:
        reply = 'Whatever.'
    return reply
