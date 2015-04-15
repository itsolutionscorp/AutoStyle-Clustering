# vim:fileencoding=utf-8

import re


def hey(words):
    if not words.strip():
        return u'Fine. Be that way!'

    is_only_numbers = re.sub('[^a-zA-Z0-9]', '', words).isdigit()

    if not is_only_numbers and words.upper() == words:
        return u'Woah, chill out!'

    if words.endswith('?'):
        return u'Sure.'

    return u'Whatever.'
