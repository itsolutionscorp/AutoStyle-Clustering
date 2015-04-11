#
# Solution for the Python "Bob" exercise.
#
from __future__ import unicode_literals

def hey(what):


    if what == '':
        return 'Fine. Be that way!'
    elif what == what.upper():
        return 'Woah, chill out!'
    elif '?' in what:
        return 'Sure.'
    else:
        return 'Whatever.'
