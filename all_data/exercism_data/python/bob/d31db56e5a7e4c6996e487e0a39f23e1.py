#
# Skeleton file for the Python "Bob" exercise.
#


def hey(input):
    input = input.strip()
    if input:
        if input.isupper():
            return 'Whoa, chill out!'
        elif input.endswith('?'):
            return 'Sure.'
        else:
            return 'Whatever.'

    return 'Fine. Be that way!'
