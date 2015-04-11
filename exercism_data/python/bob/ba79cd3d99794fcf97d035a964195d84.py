import re
def hey(string):
    if not string or not string.strip():
        return 'Fine. Be that way!'
    elif(string.upper() == string) and re.search('[A-Z]+',string):
        return 'Whoa, chill out!'
    elif re.search('.+\?$', string):
        return 'Sure.'
    else:
        return 'Whatever.'
