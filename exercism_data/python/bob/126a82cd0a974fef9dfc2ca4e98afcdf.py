#
# Skeleton file for the Python "Bob" exercise.
#

question = '?'


def hey(what):

    response = 'Whatever.'
    stripped_what = what.strip()

    if not what or what.isspace():
        response = 'Fine. Be that way!'
    elif what.isupper():
        response = 'Whoa, chill out!'
    elif stripped_what.endswith(question):
        response = 'Sure.'
    return response
