#
# Skeleton file for the Python "Bob" exercise.
#

def includes_alpha(s):
    for c in s:
        if c.isalpha():
            return True
    return False


def hey(what):
    what = what.strip()
    if not what:
        return 'Fine. Be that way!'
    if what.upper() == what and includes_alpha(what):
        return 'Whoa, chill out!'
    if what[-1] == '?': ## endswith
        return 'Sure.'
    return 'Whatever.'
