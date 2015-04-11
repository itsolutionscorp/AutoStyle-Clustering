#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
    what = what.rstrip()
    if not what:
        return 'Fine. Be that way!'
    elif what == what.upper() and what.upper() != what.lower():
        return 'Whoa, chill out!'
    elif what[-1] == '?':
        return 'Sure.'
    else:
        return 'Whatever.'
