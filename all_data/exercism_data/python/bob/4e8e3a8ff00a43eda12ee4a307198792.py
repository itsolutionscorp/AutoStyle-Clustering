#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
    if what == '' or what.isspace():
        return 'Fine. Be that way!'

    if what.isupper():
        return 'Whoa, chill out!'

    if what.endswith('?'):
        return 'Sure.'

    return 'Whatever.'
