import re

def hey(text):
    if yelling(text):
        return 'Whoa, chill out!'
    elif question(text):
        return 'Sure.'
    elif silence(text):
        return 'Fine. Be that way!'
    else:
        return 'Whatever.'

def yelling(text):
    return text.upper() == text and text.lower() != text

def question(text):
    return text.endswith('?')

def silence(text):
    return text.strip() == ''