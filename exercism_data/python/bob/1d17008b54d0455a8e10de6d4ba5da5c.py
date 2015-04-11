#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
    if not what.strip():
        return 'Fine. Be that way!'
    if what.upper() == what and what.lower() != what:
        return 'Whoa, chill out!'
    elif what.strip()[-1] == '?':
        return 'Sure.'
    return 'Whatever.'
