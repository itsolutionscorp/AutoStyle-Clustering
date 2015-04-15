#!/usr/bin/env python
""" exercism.io bob application
"""

def hey(bothersome_words):
    if bothersome_words.isupper():
        return "Whoa, chill out!"
    if bothersome_words.endswith('?'):
        return "Sure."
    if not bothersome_words.strip():
        return "Fine. Be that way!"
    return "Whatever."
