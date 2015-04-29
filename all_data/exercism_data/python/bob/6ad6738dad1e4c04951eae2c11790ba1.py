#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
    what = what.strip()
    # Check if no lowercase then shout
    if what.isupper():
        return  'Whoa, chill out!'
    elif what.endswith('?'):
        return 'Sure.'
    elif len(what) == 0:
        return 'Fine. Be that way!'
    else:
        return 'Whatever.'
