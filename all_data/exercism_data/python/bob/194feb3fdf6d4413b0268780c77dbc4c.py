import string

#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):

    what = what.strip()

    if not what:
        return 'Fine. Be that way!'

    if what.upper() == what and what.lower() != what:
        return 'Whoa, chill out!'

    if what.find('?') == len(what) - 1:
        return 'Sure.'

    if what.upper() == what.lower():
        return 'Whatever.'

    return 'Whatever.'
