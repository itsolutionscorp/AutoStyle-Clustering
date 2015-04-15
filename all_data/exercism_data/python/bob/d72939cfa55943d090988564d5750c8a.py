from __future__ import unicode_literals
#
# Skeleton file for the Python "Bob" exercise.
#
import string
import unicodedata
def hey(what):
    if not what.strip():
        return 'Fine. Be that way!'
    chars = set(unicodedata.normalize('NFKD', what))
    only_letters = (chars & set(string.letters))
    if only_letters and only_letters < set(string.uppercase):
        return 'Whoa, chill out!'
    if what.rstrip().endswith('?'):
        return 'Sure.'
    return 'Whatever.'
