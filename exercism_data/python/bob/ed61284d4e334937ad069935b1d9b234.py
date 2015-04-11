import re


def hey(speech):
    """Returns Bob's response, depending on user input."""
    if len(speech.strip()) == 0:
        return 'Fine. Be that way!'
    if speech == speech.upper() and letter_in_string(speech):
        return 'Whoa, chill out!'
    if speech[-1] == '?':
        return 'Sure.'
    return 'Whatever.'


def letter_in_string(input_string):
    """Checks if string contains at least one letter."""
    return re.search('[a-zA-Z]', input_string)
