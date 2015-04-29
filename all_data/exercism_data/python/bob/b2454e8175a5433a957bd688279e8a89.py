#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
    if not what or what.isspace():
        return 'Fine. Be that way!'

    yell = True
    at_least_one_alph = False
    for ch in what:
        if ch.isalpha():
          at_least_one_alph = True
        if ch.isalpha() and not ch.isupper():
            yell = False
    if yell and at_least_one_alph:
        return 'Whoa, chill out!'

    if what[-1] == '?':
        return 'Sure.'

    return 'Whatever.'
