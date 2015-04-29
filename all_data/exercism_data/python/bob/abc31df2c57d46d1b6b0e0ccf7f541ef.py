#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
    x = what.rstrip()
    if x.isupper():
        return 'Whoa, chill out!'
    elif x.endswith('?'):
        return 'Sure.'
    elif x == '':
        return 'Fine. Be that way!'
    else:
        return 'Whatever.'
