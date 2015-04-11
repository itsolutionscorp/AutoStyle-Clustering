#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
    what = what.strip()
    response = 'Whatever.'

    if what.endswith('?'):
        response = 'Sure.'
    if what.isupper():
        response = 'Whoa, chill out!'
    if len(what) == 0:
        response = 'Fine. Be that way!'

    return response
