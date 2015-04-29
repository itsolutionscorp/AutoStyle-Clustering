#
# Skeleton file for the Python "Bob" exercise.
#


def hey(what):
    phrase = what.strip(' ').replace('\t', '')

    if phrase == '':
        return 'Fine. Be that way!'
    elif phrase[-1:] == '?':
        if phrase.isupper():
            return 'Whoa, chill out!'
        else:
            return 'Sure.'
    elif phrase.isupper():
        return 'Whoa, chill out!'
    return 'Whatever.'
