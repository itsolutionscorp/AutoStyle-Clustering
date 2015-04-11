import re

def hey(input):
    # does the string contain no non-whitespace characters?
    if not re.search('[\S]',input):
        return 'Fine. Be that way!'
    # does the string contain alphabetic characters, and are they all uppercase?
    if re.search('[a-zA-Z]', input) and input == input.upper():
        return 'Whoa, chill out!'
    # does the string end with a question mark?
    if input[-1] == '?':
        return 'Sure.'
    # default case
    return 'Whatever.'
