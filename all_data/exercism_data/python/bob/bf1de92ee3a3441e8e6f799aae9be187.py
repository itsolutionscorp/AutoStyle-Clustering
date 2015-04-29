#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
    what = what.strip()
    response = u'Whatever.'
    if not what:
        response = u'Fine. Be that way!'
    elif what.isupper() and not what.islower():
        response = u'Whoa, chill out!'
    elif what.endswith('?'):
        response = u'Sure.'
    return response
