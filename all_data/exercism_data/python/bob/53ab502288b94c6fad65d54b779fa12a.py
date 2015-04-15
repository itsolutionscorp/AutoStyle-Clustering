import re
def hey(string):
    if not string or not string.strip():
        return 'Fine. Be that way!'
    if(string.upper() == string) and re.search('[A-Z]+',string):
        return 'Whoa, chill out!'
    if re.search('.+\?$', string):
        return 'Sure.'
    elif string:
        return 'Whatever.'
