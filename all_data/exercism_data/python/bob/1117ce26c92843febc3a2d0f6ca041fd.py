#
# Skeleton file for the Python "Bob" exercise.
#

def hey(phrase):
    if phrase.isupper():
        return 'Whoa, chill out!'
    elif phrase.strip().endswith('?'):
        return 'Sure.'
    elif phrase.isspace() or not phrase.strip():
        return 'Fine. Be that way!'
    return 'Whatever.'
