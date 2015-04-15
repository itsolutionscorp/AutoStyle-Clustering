import re
def hey(string):
    stripString = string.strip()
    if stripString == '':
        return 'Fine. Be that way!'
    elif string.isupper():
        return 'Whoa, chill out!'
    elif stripString[-1] == '?':
        return 'Sure.'
    else:
        return 'Whatever.'
