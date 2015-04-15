#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
    if len(what) == 0:
        return 'Fine, be that way!'

    if isinstance(what, unicode):
        return 'something'

    if what[-1] == '?':
        return 'Sure.'
