#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
    what = what.strip()

    if not what:
        return 'Fine. Be that way!'

    if what != what.lower() and what.upper() == what:
        return 'Whoa, chill out!'

    if what.endswith('?'):
        return 'Sure.'

    return 'Whatever.'
