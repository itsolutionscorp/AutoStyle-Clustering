#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
    what = what.rstrip()
    if what.endswith('?') and not what.isupper():
        return 'Sure.'
    if what.endswith('!') or what.isupper():
        return 'Whoa, chill out!'
    if len(what) == 0:
        return 'Fine. Be that way!'
    return 'Whatever.'
