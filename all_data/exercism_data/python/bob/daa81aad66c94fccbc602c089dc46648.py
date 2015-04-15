import re


def hey(prompt):

    # Shouting
    if prompt.isupper():
        return 'Whoa, chill out!'

    # Questions
    if re.match(r"^.*\?$", prompt.strip()):
        return 'Sure.'

    # Silences
    if '' == prompt.strip():
        return 'Fine. Be that way!'

    # Numbers with possible whitespace
    if re.match(r"^[0-9\s]?$", prompt.strip()):
        return 'Whatever.'

    # Default response
    return 'Whatever.'
