#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
    response = 'Whatever.'

    if (what.isupper()):
        response = 'Whoa, chill out!'
    elif (what.endswith('?')):
        response = 'Sure.'
    elif (what.strip() == ''):
        response = 'Fine. Be that way!'
    return response
