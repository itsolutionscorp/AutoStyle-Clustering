#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
    if what.upper() == what and what.lower() != what:
        return 'Whoa, chill out!'
    elif what.strip().endswith('?'):
        return 'Sure.'
    elif not what.strip():
        return 'Fine. Be that way!'
    else:
        return 'Whatever.'