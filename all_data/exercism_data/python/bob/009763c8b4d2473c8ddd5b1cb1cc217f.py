#
# Skeleton file for the Python "Bob" exercise.
#

def hey(what):
    if what.isupper():
        # All letters are uppercase.
        return 'Whoa, chill out!'

    if not what.rstrip():
        # There are no words.
        return 'Fine. Be that way!'

    if what[-1] == '?':
        # There is a question mark at the end of the string.
        return 'Sure.'

    return 'Whatever.'
