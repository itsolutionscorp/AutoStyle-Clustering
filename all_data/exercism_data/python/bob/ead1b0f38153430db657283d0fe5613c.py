#
# Skeleton file for the Python "Bob" exercise.
#
import unicodedata

def hey(what):
    what = unicodedata.normalize('NFD', what.strip())
    what = ''.join(c for c in what if not unicodedata.combining(c))
    print(what)

    if not what:
        return 'Fine. Be that way!'
    if what.endswith('?') and not what[0:-1].isupper():
        return 'Sure.'
    if what[0:-1].isupper() or what[0:-1].isupper() and what.endswith('!'):
        return 'Whoa, chill out!'
    return 'Whatever.'
