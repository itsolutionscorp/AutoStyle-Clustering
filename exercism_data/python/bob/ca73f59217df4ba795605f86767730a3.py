import re

def is_question(phrase):
    return bool(re.match(r".*\?$", phrase))


def is_yelling(phrase):
    return re.search("[A-Z]", phrase) and phrase.upper() == phrase


def hey(phrase):
    response = "Whatever."
    phrase = phrase.strip()
    if not phrase:
        response = "Fine. Be that way!"
    if is_question(phrase):
        response = "Sure."
    if is_yelling(phrase):
        response = "Woah, chill out!"
    return response
