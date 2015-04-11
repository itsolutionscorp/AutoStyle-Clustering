#
# Skeleton file for the Python "Bob" exercise.
#


def hey(phrase):
    if not phrase.strip() == '':
        if any(c.isalpha() for c in phrase) and not any(
                c.islower() for c in phrase):
            return 'Whoa, chill out!'
        elif phrase.endswith('?'):
            return 'Sure.'
        return 'Whatever.'
    return 'Fine. Be that way!'
