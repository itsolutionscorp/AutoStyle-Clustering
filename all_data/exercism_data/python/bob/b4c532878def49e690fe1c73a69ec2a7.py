#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):

    if len(what.strip()) == 0:
        return 'Fine. Be that way!'

    if sum(map(unicode.isalpha, what)) > 0:
        if sum(map(unicode.islower, what)) == 0:
            return 'Whoa, chill out!'

    if what.strip()[-1] == '?':
        return 'Sure.'

    return 'Whatever.'
