#
# Skeleton file for the Python "Bob" exercise.
#


def hey(what):
    phrase = what.strip(' ').replace('\t', '')
    text = phrase[-1:]
    if phrase == '':
        return 'Fine. Be that way!'
    elif text == '?':
        if phrase.isupper():
            return 'Whoa, chill out!'
        else:
            return 'Sure.'
    elif phrase.isupper():
        return 'Whoa, chill out!'
    return 'Whatever.'
