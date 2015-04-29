#
# Skeleton file for the Python "Bob" exercise.
#


def hey(phrase):
    if not phrase.strip() == '':
        if phrase.isupper():
            return 'Whoa, chill out!'
        elif phrase.endswith('?'):
            return 'Sure.'
        return 'Whatever.'
    return 'Fine. Be that way!'
