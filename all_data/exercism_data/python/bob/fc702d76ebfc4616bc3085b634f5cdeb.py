#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
    what = what.strip()
    if not what:
        return 'Fine. Be that way!'
    if what == what.upper() and len(filter(lambda c: c.isalpha(), what)) > 0:
        return 'Whoa, chill out!'
    if what[-1] == '?':
        return 'Sure.'
    return 'Whatever.'
