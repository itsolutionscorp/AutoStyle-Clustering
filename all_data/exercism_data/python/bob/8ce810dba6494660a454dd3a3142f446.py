import re


def hey(phrase):
    if not phrase.strip():
        return 'Fine. Be that way!'
    alpha = re.search(r'([a-z]|[A-Z])', phrase)
    # TODO: Doesn't handle unicode...
    lowercase = re.search(r'[a-z]', phrase)
    if lowercase is None and alpha is not None:
        return 'Woah, chill out!'
    if phrase.strip()[-1] == '?':
        return 'Sure.'
    return 'Whatever.'
