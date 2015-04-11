import re

def hey(input):

    clean_input = input.strip()

    if clean_input == '':
        return 'Fine. Be that way!'

    if clean_input.isupper():
        return 'Whoa, chill out!'

    if clean_input.endswith('?'):
        return 'Sure.'

    return 'Whatever.'
