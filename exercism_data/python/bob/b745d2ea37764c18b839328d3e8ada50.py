#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
    # Removing space a head of time. Had problems when doing it in if block.
    what = what.strip()
    if what == '':
        return 'Fine. Be that way!'
    elif what.isupper():
        return 'Whoa, chill out!'
    elif what[-1] == '?':
        return 'Sure.'
    else:
        return 'Whatever.'
