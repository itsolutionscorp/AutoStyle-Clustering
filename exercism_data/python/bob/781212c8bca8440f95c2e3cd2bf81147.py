#
# Skeleton file for the Python "Bob" exercise.
#


def hey(phrase):
    if phrase.isupper():
        return 'Whoa, chill out!'
    elif phrase.endswith("?"):
        return 'Sure.'
    elif phrase.strip() == "":
        return 'Fine. Be that way!'
    else:
        return 'Whatever.'
