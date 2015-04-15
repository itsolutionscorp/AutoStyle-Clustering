import re


def hey(message):
    """ Bob's hey method, takes a string as input and return's Bob's response """

    if message.isupper():
        return 'Whoa, chill out!'

    if message.endswith("?"):
        return 'Sure.'

    if re.search(r'^\s*$', message):
        return 'Fine. Be that way!'
    return 'Whatever.'
