#
# Skeleton file for the Python "Bob" exercise.
#

def hey(what):
    what = what.strip()
    if type(what) == unicode:
        if what.isupper():
            return 'Whoa, chill out!'
        if what[-1:] == '?':
            return 'Sure.'
        if what == '':
            return 'Fine. Be that way!'
    return 'Whatever.'
