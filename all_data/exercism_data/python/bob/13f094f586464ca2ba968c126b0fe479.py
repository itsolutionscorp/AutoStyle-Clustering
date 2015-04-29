#!/usr/bin/env python

def hey(phrase):
    if phrase.isupper():
        response = "Woah, chill out!"
    elif phrase.endswith('?'):
        response = "Sure."
    elif not phrase.strip():
        return "Fine. Be that way!"
    else:
        response = "Whatever."

    return response


if __name__ == '__main__':
    hey("test")
