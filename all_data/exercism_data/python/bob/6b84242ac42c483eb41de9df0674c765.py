#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
    what = what.strip()
    if len(what) == 0:
        return 'Fine. Be that way!'
    elif what == what.upper() and not what == what.lower():
        return 'Whoa, chill out!'
    elif what[-1] == '?':
        return 'Sure.'
    else:
        return 'Whatever.'
