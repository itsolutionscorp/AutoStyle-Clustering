#!/usr/bin/python


def hey(sentence):
    """Generates answers in a conversation"""
    if not sentence or sentence.isspace():
        return "Fine. Be that way!"
    if sentence.isupper():
        return "Woah, chill out!"
    if sentence[-1] == '?':
        return "Sure."
    return "Whatever."
