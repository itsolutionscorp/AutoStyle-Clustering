#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
    what = what.strip()
    if not what:
        return 'Fine. Be that way!'
    if what == what.upper() and what != what.lower():
        return 'Whoa, chill out!'
    if what[-1] == '?':
        return 'Sure.'
    return 'Whatever.'
