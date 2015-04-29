#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
    what = what.strip()
    if not what:
        return 'Fine. Be that way!'
    elif what.upper() == what and any(c.isalpha() for c in what):
        return 'Whoa, chill out!'
    elif what.endswith('?'):
        return 'Sure.'
    else:
        return 'Whatever.'
