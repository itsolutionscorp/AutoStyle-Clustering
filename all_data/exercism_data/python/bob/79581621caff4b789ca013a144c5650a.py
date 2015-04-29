#
# Skeleton file for the Python "Bob" exercise.
#

def hey(message):

    words = message.strip()

    if words == '':
        response = 'Fine. Be that way!'

    elif words.isupper():
        response = 'Whoa, chill out!'

    elif words.endswith('?'):
        response = 'Sure.'

    else:
        response = 'Whatever.'

    return response
