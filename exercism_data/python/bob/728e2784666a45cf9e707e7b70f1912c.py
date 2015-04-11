#
# Skeleton file for the Python "Bob" exercise.
#


def hey(what):
    what = unicode(what.strip())
    alpha = filter(unicode.isalpha, what)
    upper = filter(unicode.isupper, alpha)

    if not what:
        return 'Fine. Be that way!'

    if upper and len(alpha) == len(upper):
        return 'Whoa, chill out!'

    if what[-1] == '?':
        return 'Sure.'

    return 'Whatever.'
