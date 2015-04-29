#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):

    what = what.strip()

    if what.upper() == what and what.lower() != what:
        return 'Whoa, chill out!'

    if what.endswith('?'):
        return 'Sure.'

    if not what:
        return 'Fine. Be that way!'

    return 'Whatever.'
