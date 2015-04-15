import re
def hey(string):
    if string.strip()== '':
        return 'Fine. Be that way!'
    elif string.isupper():
        return 'Whoa, chill out!'
    elif re.search('.+\?$', string):
        return 'Sure.'
    else:
        return 'Whatever.'
