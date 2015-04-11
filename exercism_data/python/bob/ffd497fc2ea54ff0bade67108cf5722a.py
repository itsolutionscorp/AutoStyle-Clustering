#
# Skeleton file for the Python "Bob" exercise.
#
def hey(message):

    if message is None or message.strip() == '':
        return 'Fine. Be that way!'

    elif message.isupper():
        return 'Whoa, chill out!'

    strip = message.rstrip()
    if strip.endswith('?') is True:
        return 'Sure.'

    else:
        return 'Whatever.'
