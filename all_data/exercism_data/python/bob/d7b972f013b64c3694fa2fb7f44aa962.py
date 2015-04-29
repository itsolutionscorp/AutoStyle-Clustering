#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
    chill = u'Whoa, chill out!'
    sure = u'Sure.'
    fine = u'Fine. Be that way!'

    response = u'Whatever.'

    if what.isupper():
        response = chill
    elif what.endswith('?'):
        response = sure
    elif what.isalpha() or what.isspace() or what == '':
        response = fine

    return response
