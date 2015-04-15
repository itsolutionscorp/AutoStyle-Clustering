import re

responses = {
    "whatever": "Whatever.",
    "chillOut": "Whoa, chill out!",
    "sure": "Sure.",
    "fine": "Fine. Be that way!"
}

def hey(what):

    # Remove preceding and trailing whitespace
    what = what.strip()

    if what == '':
        return responses['fine']

    if what == what.upper() and re.search(r'[A-Z]', what):
        return responses['chillOut']

    if what.endswith('?'):
        return responses['sure']

    return responses['whatever']
