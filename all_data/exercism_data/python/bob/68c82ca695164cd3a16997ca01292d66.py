#
# Skeleton file for the Python "Bob" exercise.
#
RESP = {'sure': 'Sure.', 'whoa': 'Whoa, chill out!',
        'whatever': 'Whatever.', 'fine': 'Fine. Be that way!'}

def hey(what):
    what = what.strip()
    if len(what) == 0:
        return RESP['fine']
    if what.isupper():
        return RESP['whoa']
    if what[-1] == '?':
        return RESP['sure']
    return RESP['whatever']
