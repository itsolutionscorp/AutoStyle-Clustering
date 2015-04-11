# -*- coding: utf-8 -*-


def hey(text=None):
    if text.strip() == '':
        return "Fine. Be that way!"
    letters_only = filter(lambda x: x.isalpha(), text)
    if letters_only.isupper():
        return "Whoa, chill out!"
    elif text.endswith('?'):
        return "Sure."
    else:
        return "Whatever."
