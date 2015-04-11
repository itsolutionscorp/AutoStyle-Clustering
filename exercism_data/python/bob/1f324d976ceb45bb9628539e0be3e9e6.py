# CODING=utf-8
# Skeleton file for the Python "Bob" exercise.
#
import string

CASE_INDIFFERENT = string.whitespace + \
        string.punctuation + string.digits

def all_case_indifferent(text):
    return all(map(lambda x: x in CASE_INDIFFERENT, text))

def is_shouting(text):
    return not all_case_indifferent(text) and text == text.upper()

def hey(what):
    stripped = what.strip()
    if not stripped:
        return 'Fine. Be that way!'
    elif is_shouting(stripped):
        return 'Whoa, chill out!'
    elif stripped.endswith('?'):
        return 'Sure.'
    else:
        return 'Whatever.'
