import re

def hey(a_string):
    a_string = a_string.strip()

    if not a_string:
        return 'Fine. Be that way!'
    elif a_string.isupper():
        return 'Woah, chill out!'
    elif a_string[-1] == '?':
        return 'Sure.'
    else:
        return 'Whatever.'
