#! /usr/bin/env python

def is_shouting(text):
    return any(c.isalpha() for c in text) and text.upper() == text

def is_silence(text):
    return text.strip() == ''

def is_question(text):
    return text.endswith('?')

class Bob(object):
    def hey(self, msg):
        if is_silence(msg):
            return "Fine. Be that way!"
        if is_shouting(msg):
            return "Woah, chill out!"
        if is_question(msg):
            return "Sure."
        return "Whatever."
