#
# Skeleton file for the Python "Bob" exercise.
#


def hey(input):
    input = input.strip()
    if input:
        if input.isupper():
            return 'Whoa, chill out!'
        elif input[-1] == '?':
            return 'Sure.'
        else:
            return 'Whatever.'

    return 'Fine. Be that way!'
