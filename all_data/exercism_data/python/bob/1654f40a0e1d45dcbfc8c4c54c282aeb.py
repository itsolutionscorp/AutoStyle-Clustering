def is_question(phrase):
    return phrase.endswith("?")


def is_yelling(phrase):
    return phrase.isupper()


def hey(phrase):
    response = "Whatever."
    phrase = phrase.strip()
    if not phrase:
        response = "Fine. Be that way!"
    elif is_yelling(phrase):
        response = "Woah, chill out!"
    elif is_question(phrase):
        response = "Sure."
    return response
