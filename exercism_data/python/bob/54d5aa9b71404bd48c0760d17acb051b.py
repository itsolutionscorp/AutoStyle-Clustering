from __future__ import unicode_literals


def hey(thats_what_she_said):
    if thats_what_she_said.isupper():
        return 'Whoa, chill out!'
    elif thats_what_she_said.endswith('?'):
        return 'Sure.'
    elif thats_what_she_said.isspace() or not thats_what_she_said:
        return 'Fine. Be that way!'
    return 'Whatever.'
