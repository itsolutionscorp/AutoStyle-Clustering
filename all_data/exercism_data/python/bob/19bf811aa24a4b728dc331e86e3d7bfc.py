#
# Skeleton file for the Python "Bob" exercise.
#
def hey(message):
    message = message.strip()

    # Checks

    if not message:
        return 'Fine. Be that way!'
    elif message.isupper():
        return 'Whoa, chill out!'
    elif message.endswith('?'):
        return 'Sure.'
    else:
        return 'Whatever.'
