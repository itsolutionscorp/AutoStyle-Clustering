#
# Skeleton file for the Python "Bob" exercise.
#
import re

def hey(what):
    if len(re.sub(r'\s', '', what)) == 0:
        return 'Fine. Be that way!'
    if what == what.upper() and what != what.lower():
        return 'Whoa, chill out!'
    if what.rstrip().endswith('?'):
        return 'Sure.'
    return 'Whatever.'
