#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
    what_stripped = what.strip()
    if what_stripped.isupper() or (what_stripped.isupper() and what_stripped.endswith('?')):
        return 'Whoa, chill out!'
    if what_stripped.endswith('?'):
        return 'Sure.'
    elif not what_stripped:
        return 'Fine. Be that way!'
    else:
        return 'Whatever.'
