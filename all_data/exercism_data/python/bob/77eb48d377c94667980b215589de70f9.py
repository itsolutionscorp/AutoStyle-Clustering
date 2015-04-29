#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):

    # preprocess
    what = what.strip()
    what_alpha = ''.join([l for l in what if l.isalpha()])

    # logic
    if not what:
        return 'Fine. Be that way!'

    if what_alpha and all([l.isupper() for l in what_alpha]):
        return 'Whoa, chill out!'

    if what[-1] == '?':
        return 'Sure.'

    return 'Whatever.'
