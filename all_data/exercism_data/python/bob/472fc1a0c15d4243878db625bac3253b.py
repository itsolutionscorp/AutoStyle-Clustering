import re


def hey(phrase):
    phrase = phrase.strip()

    if not phrase:
        return "Fine. Be that way!"

    contains_letters = re.compile(r'.*[^\W\d_]', re.UNICODE)
    if phrase.upper() == phrase and contains_letters.match(phrase):
        return "Woah, chill out!"

    if phrase[-1] == "?":
        return "Sure."
    return "Whatever."
