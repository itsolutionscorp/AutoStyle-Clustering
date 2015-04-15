#
# Skeleton file for the Python "Bob" exercise.
#


def hey(what):
    what = what.strip()
    if what == '':
        return "Fine. Be that way!"
    if what.isupper() == True:
        return "Whoa, chill out!"
    if what is None:
        return "Fine. Be that way!"
    if what.endswith('?'):
        return "Sure."
    return 'Whatever.'
