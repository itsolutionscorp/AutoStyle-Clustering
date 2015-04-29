import re

def hey(a_string):
    if not a_string or re.search('^\s+$', a_string):
        return 'Fine. Be that way!'
    elif a_string[-1] == '!' or re.search('^[A-Z]+$', a_string.replace(' ','')):
        if a_string.upper() == a_string:
            return 'Woah, chill out!'
        else:
            return 'Whatever.'
    elif a_string[-1] == '?':
        if a_string.upper() == a_string and not re.search('^\d+\?', a_string):
            return 'Woah, chill out!'
        else:
            return 'Sure.'
    else:
        return 'Whatever.'
