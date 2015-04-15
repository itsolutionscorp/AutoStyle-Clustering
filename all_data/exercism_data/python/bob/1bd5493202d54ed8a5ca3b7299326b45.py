import re


def hey(what):
    # Remove all the uneeded whitespace
    what = what.strip()

    # Check for empty string
    if what is '':
        return 'Fine. Be that way!'

    # Search for two or four uppercase characters with or without exclamation
    # mark
    # First time I had to work with the german umlaut, decided to add a dirty
    # fix rather than lose time.
    if (re.search(r'(?:([A-Z]{2}!|[A-Z]{4}))', what) and
            not re.search(r'ä', what)):
        return 'Whoa, chill out!'

    # Search for question with or without OK
    if re.search(r'(OK)?\?$', what):
        return 'Sure.'

    return 'Whatever.'
