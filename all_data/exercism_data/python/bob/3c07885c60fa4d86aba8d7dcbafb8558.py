#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
    what = what.strip()
    if what == '':
        return 'Fine. Be that way!'
    if is_yelling(what):
        return 'Whoa, chill out!'
    if  '?' in what and what[-1] != '.':
        return 'Sure.'
    return 'Whatever.'

def is_yelling(what):
    if what.isupper():
        return 1
