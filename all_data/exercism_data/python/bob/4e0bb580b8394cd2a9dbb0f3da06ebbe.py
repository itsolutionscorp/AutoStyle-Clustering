import re

def hey(s):
    if is_caps(s):
        return "Whoa, chill out!"
    if is_question(s):
        return "Sure."
    if s.strip() == "":
        return "Fine. Be that way!"
    return "Whatever."

def is_question(s):
    return s.endswith("?")

def contains_letters(s):
    return re.search("[a-zA-Z]+", s) is not None

def is_caps(s):
    return contains_letters(s) and s.upper() == s
