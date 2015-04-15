#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
    w = what.strip()
    if not w:
        res = 'Fine. Be that way!'
    elif w.isupper():
        res = 'Whoa, chill out!'
    elif w.endswith('?'):
        res = 'Sure.'
    else:
        res = 'Whatever.'
    return res
