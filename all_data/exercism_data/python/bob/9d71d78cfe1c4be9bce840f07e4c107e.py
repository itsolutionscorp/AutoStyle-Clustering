""" Bob implemented"""
#
# Skeleton file for the Python "Bob" exercise.
#


def hey(what):
    """hey Bob file"""
    if what == '' or what.isspace():
        return 'Fine. Be that way!'
    if what.upper() == what and\
            what.lower() != what:
        return 'Whoa, chill out!'
    if what.strip()[-1] == '?':
        return 'Sure.'
    return 'Whatever.'
